/**
 * Copyright 2013 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY JogAmp Community ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JogAmp Community OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of JogAmp Community.
 */

package com.jogamp.common.util;

import java.io.PrintStream;

/**
 * Helper class to provide a Runnable queue implementation with a Runnable wrapper
 * which notifies after execution for the <code>invokeAndWait()</code> semantics.
 */
public class FunctionTask<R,A> extends TaskBase implements Function<R,A> {
    protected Function<R,A> runnable;
    protected R result;
    protected A[] args;

    /**
     * Invokes <code>func</code>.
     * @param waitUntilDone if <code>true</code>, waits until <code>func</code> execution is completed, otherwise returns immediately.
     * @param func the {@link Function} to execute.
     * @param args the {@link Function} arguments
     * @return the {@link Function} return value
     */
    public static <U,V> U invoke(boolean waitUntilDone, Function<U,V> func, V... args) {
        Throwable throwable = null;
        final Object sync = new Object();
        final FunctionTask<U,V> rt = new FunctionTask<U,V>( func, waitUntilDone ? sync : null, true, waitUntilDone ? null : System.err );
        final U res;
        synchronized(sync) {
            res = rt.eval(args);
            if( waitUntilDone ) {
                try {
                    sync.wait();
                } catch (InterruptedException ie) {
                    throwable = ie;
                }
                if(null==throwable) {
                    throwable = rt.getThrowable();
                }
                if(null!=throwable) {
                    throw new RuntimeException(throwable);
                }
            }
        }
        return res;
    }

    /**
     * Create a RunnableTask object w/ synchronization,
     * ie. suitable for <code>invokeAndWait()</code>.
     *
     * @param runnable the user action
     * @param syncObject the synchronization object the caller shall wait until <code>runnable</code> execution is completed,
     *                   or <code>null</code> if waiting is not desired.
     * @param catchExceptions Influence an occurring exception during <code>runnable</code> execution.
     *                        If <code>true</code>, the exception is silenced and can be retrieved via {@link #getThrowable()},
     *                        otherwise the exception is thrown.
     * @param exceptionOut If not <code>null</code>, exceptions are written to this {@link PrintStream}.
     */
    public FunctionTask(Function<R,A> runnable, Object syncObject, boolean catchExceptions, PrintStream exceptionOut) {
        super(syncObject, catchExceptions, exceptionOut);
        this.runnable = runnable ;
        result = null;
        args = null;
    }

    /** Return the user action */
    public final Function<R,A> getRunnable() {
        return runnable;
    }

    /**
     * Sets the arguments for {@link #run()}.
     * They will be cleared after calling {@link #run()} or {@link #eval(Object...)}.
     */
    public final void setArgs(A... args) {
        this.args = args;
    }

    /**
     * Retrieves the cached result of {@link #run()}
     * and is cleared within this method.
     */
    public final R getResult() {
        final R res = result;
        result = null;
        return res;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Calls {@link #eval(Object...)}.
     * </p>
     * <p>
     * You may set the {@link #eval(Object...)} arguments via {@link #setArgs(Object...)}
     * and retrieve the result via {@link #getResult()}.
     * </p>
     */
    @Override
    public final void run() {
        final A[] args = this.args;
        this.args = null;
        this.result = null;
        runnableException = null;
        tStarted = System.currentTimeMillis();
        if(null == syncObject) {
            try {
                this.result = runnable.eval(args);
            } catch (Throwable t) {
                runnableException = t;
                if(null != exceptionOut) {
                    exceptionOut.println("FunctionTask.run(): "+getExceptionOutIntro()+" exception occured on thread "+Thread.currentThread().getName()+": "+toString());
                    printSourceTrace();
                    t.printStackTrace(exceptionOut);
                }
                if(!catchExceptions) {
                    throw new RuntimeException(runnableException);
                }
            } finally {
                tExecuted = System.currentTimeMillis();
            }
        } else {
            synchronized (syncObject) {
                try {
                    this.result = runnable.eval(args);
                } catch (Throwable t) {
                    runnableException = t;
                    if(null != exceptionOut) {
                        exceptionOut.println("FunctionTask.run(): "+getExceptionOutIntro()+" exception occured on thread "+Thread.currentThread().getName()+": "+toString());
                        printSourceTrace();
                        t.printStackTrace(exceptionOut);
                    }
                    if(!catchExceptions) {
                        throw new RuntimeException(runnableException);
                    }
                } finally {
                    tExecuted = System.currentTimeMillis();
                    syncObject.notifyAll();
                }
            }
        }
    }

    @Override
    public final R eval(A... args) {
        this.args = args;
        run();
        final R res = result;
        result = null;
        return res;
    }
}

