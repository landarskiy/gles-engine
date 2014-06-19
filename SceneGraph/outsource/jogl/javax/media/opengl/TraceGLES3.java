package javax.media.opengl;

import java.io.*;
import javax.media.opengl.*;
import com.jogamp.gluegen.runtime.*;
import java.nio.*;
import javax.media.opengl.GLES3;
import javax.media.opengl.GL4ES3;
import javax.media.opengl.GLES2;

/**
 * <p>
 * Composable pipeline which wraps an underlying {@link GL} implementation,
 * providing tracing information to a user-specified {@link java.io.PrintStream}
 * before and after each OpenGL method call.
 * </p>
 * <p>
 * Sample code which installs this pipeline:
 * <pre>
 *   gl = drawable.setGL(new TraceGL(drawable.getGL(), System.err));
 * </pre>
 * For automatic instantiation see {@link GLPipelineFactory#create(String, Class, GL, Object[])}
 * </p>
 */
public class TraceGLES3 implements javax.media.opengl.GLES2, javax.media.opengl.GL4ES3, javax.media.opengl.GLES3{
  public static final boolean DEBUG = jogamp.opengl.Debug.debug("TraceGLES3");
  public TraceGLES3(GLES3 downstreamGLES3, PrintStream stream)
  {
    if (downstreamGLES3 == null) {
      throw new IllegalArgumentException("null downstreamGLES3");
    }
    this.downstreamGLES3 = downstreamGLES3;
    this.stream = stream;
  }

  @Override
  public final boolean isGL() {
    return true;
  }
  @Override
  public final boolean isGL4bc() {
    return downstreamGLES3.isGL4bc();
  }
  @Override
  public final boolean isGL4() {
    return downstreamGLES3.isGL4();
  }
  @Override
  public final boolean isGL3bc() {
    return downstreamGLES3.isGL3bc();
  }
  @Override
  public final boolean isGL3() {
    return downstreamGLES3.isGL3();
  }
  @Override
  public final boolean isGL2() {
    return downstreamGLES3.isGL2();
  }
  @Override
  public final boolean isGLES1() {
    return downstreamGLES3.isGLES1();
  }
  @Override
  public final boolean isGLES2() {
    return downstreamGLES3.isGLES2();
  }
  @Override
  public final boolean isGLES3() {
    return downstreamGLES3.isGLES3();
  }
  @Override
  public final boolean isGL2ES1() {
    return downstreamGLES3.isGL2ES1();
  }
  @Override
  public final boolean isGL2ES2() {
    return downstreamGLES3.isGL2ES2();
  }
  @Override
  public final boolean isGL2ES3() {
    return downstreamGLES3.isGL2ES3();
  }
  @Override
  public final boolean isGL3ES3() {
    return downstreamGLES3.isGL3ES3();
  }
  @Override
  public final boolean isGL4ES3() {
    return downstreamGLES3.isGL4ES3();
  }
  @Override
  public final boolean isGL2GL3() {
    return downstreamGLES3.isGL2GL3();
  }
  @Override
  public final boolean isGLES() {
    return downstreamGLES3.isGLES();
  }
  @Override
  public final boolean isGL4core() {
    return downstreamGLES3.isGL4core();
  }
  @Override
  public final boolean isGL3core() {
    return downstreamGLES3.isGL3core();
  }
  @Override
  public final boolean isGLcore() {
    return downstreamGLES3.isGLcore();
  }
  @Override
  public final boolean isGLES2Compatible() {
    return downstreamGLES3.isGLES2Compatible();
  }
  @Override
  public final boolean isGLES3Compatible() {
    return downstreamGLES3.isGLES3Compatible();
  }
  @Override
  public final javax.media.opengl.GL getGL() {
    return this;
  }
  @Override
  public final javax.media.opengl.GL4bc getGL4bc() {
    throw new GLException("Not a GL4bc implementation");
  }
  @Override
  public final javax.media.opengl.GL4 getGL4() {
    throw new GLException("Not a GL4 implementation");
  }
  @Override
  public final javax.media.opengl.GL3bc getGL3bc() {
    throw new GLException("Not a GL3bc implementation");
  }
  @Override
  public final javax.media.opengl.GL3 getGL3() {
    throw new GLException("Not a GL3 implementation");
  }
  @Override
  public final javax.media.opengl.GL2 getGL2() {
    throw new GLException("Not a GL2 implementation");
  }
  @Override
  public final javax.media.opengl.GLES1 getGLES1() {
    throw new GLException("Not a GLES1 implementation");
  }
  @Override
  public final javax.media.opengl.GLES2 getGLES2() {
    if( isGLES2() ) { return this; }
    throw new GLException("Not a GLES2 implementation");
  }
  @Override
  public final javax.media.opengl.GLES3 getGLES3() {
    if( isGLES3() ) { return this; }
    throw new GLException("Not a GLES3 implementation");
  }
  @Override
  public final javax.media.opengl.GL2ES1 getGL2ES1() {
    throw new GLException("Not a GL2ES1 implementation");
  }
  @Override
  public final javax.media.opengl.GL2ES2 getGL2ES2() {
    if( isGL2ES2() ) { return this; }
    throw new GLException("Not a GL2ES2 implementation");
  }
  @Override
  public final javax.media.opengl.GL2ES3 getGL2ES3() {
    if( isGL2ES3() ) { return this; }
    throw new GLException("Not a GL2ES3 implementation");
  }
  @Override
  public final javax.media.opengl.GL3ES3 getGL3ES3() {
    if( isGL3ES3() ) { return this; }
    throw new GLException("Not a GL3ES3 implementation");
  }
  @Override
  public final javax.media.opengl.GL4ES3 getGL4ES3() {
    if( isGL4ES3() ) { return this; }
    throw new GLException("Not a GL4ES3 implementation");
  }
  @Override
  public final javax.media.opengl.GL2GL3 getGL2GL3() {
    throw new GLException("Not a GL2GL3 implementation");
  }
  @Override
  public final GL getDownstreamGL() throws GLException {
    return downstreamGLES3;
  }
  @Override
  public final GLProfile getGLProfile() {
    return downstreamGLES3.getGLProfile();
  }
  @Override
  public int glGetFragDataLocation(int arg0,java.lang.String arg1)
  {
    printIndent();
    print(    "glGetFragDataLocation("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.lang.String> "+arg1+")");
    int _res = downstreamGLES3.glGetFragDataLocation(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public int glGetGraphicsResetStatus()
  {
    printIndent();
    print(    "glGetGraphicsResetStatus("+")");
    int _res = downstreamGLES3.glGetGraphicsResetStatus();
    println(" = "+_res);
    return _res;
  }
  @Override
  public int glGetUniformLocation(int arg0,java.lang.String arg1)
  {
    printIndent();
    print(    "glGetUniformLocation("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.lang.String> "+arg1+")");
    int _res = downstreamGLES3.glGetUniformLocation(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glDepthRange(double arg0,double arg1)
  {
    printIndent();
    print(    "glDepthRange("+"<double> "+arg0+", "+"<double> "+arg1+")");
    downstreamGLES3.glDepthRange(arg0,arg1);
    println("");
  }
  @Override
  public void glDrawElementsInstanced(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
    printIndent();
    print(    "glDrawElementsInstanced("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.Buffer> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glDrawElementsInstanced(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDepthFunc(int arg0)
  {
    printIndent();
    print(    "glDepthFunc("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glDepthFunc(arg0);
    println("");
  }
  @Override
  public int glGetUniformBlockIndex(int arg0,java.lang.String arg1)
  {
    printIndent();
    print(    "glGetUniformBlockIndex("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.lang.String> "+arg1+")");
    int _res = downstreamGLES3.glGetUniformBlockIndex(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glSamplerParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glSamplerParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glSamplerParameteriv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDebugMessageInsert(int arg0,int arg1,int arg2,int arg3,int arg4,java.lang.String arg5)
  {
    printIndent();
    print(    "glDebugMessageInsert("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<java.lang.String> "+arg5+")");
    downstreamGLES3.glDebugMessageInsert(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glUniform4fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetBooleanv(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print(    "glGetBooleanv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg1+")");
    downstreamGLES3.glGetBooleanv(arg0,arg1);
    println("");
  }
  @Override
  public void glGetShaderInfoLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetShaderInfoLog("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetShaderInfoLog(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttrib2f(int arg0,float arg1,float arg2)
  {
    printIndent();
    print(    "glVertexAttrib2f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+", "+"<float> "+arg2+")");
    downstreamGLES3.glVertexAttrib2f(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glObjectLabel(int arg0,int arg1,int arg2,byte[] arg3,int arg4)
  {
    printIndent();
    print(    "glObjectLabel("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glObjectLabel(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniformMatrix4x3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix4x3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix4x3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glClearBufferfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glClearBufferfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glClearBufferfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform4fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glUniform4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glUniform4fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glCompileShader(int arg0)
  {
    printIndent();
    print(    "glCompileShader("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glCompileShader(arg0);
    println("");
  }
  @Override
  public void glExtGetProgramsQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glExtGetProgramsQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glExtGetProgramsQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glExtGetProgramsQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glExtGetProgramsQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glExtGetProgramsQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glInsertEventMarkerEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print(    "glInsertEventMarkerEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg1+")");
    downstreamGLES3.glInsertEventMarkerEXT(arg0,arg1);
    println("");
  }
  @Override
  public void glRenderbufferStorageMultisampleNV(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glRenderbufferStorageMultisampleNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glRenderbufferStorageMultisampleNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniformMatrix4x3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix4x3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix4x3fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glClearBufferfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glClearBufferfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glClearBufferfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDrawElementsInstanced(int arg0,int arg1,int arg2,long arg3,int arg4)
  {
    printIndent();
    print(    "glDrawElementsInstanced("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<long> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glDrawElementsInstanced(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glBindFramebuffer(int arg0,int arg1)
  {
    printIndent();
    print(    "glBindFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBindFramebuffer(arg0,arg1);
    println("");
  }
  @Override
  public void glObjectPtrLabel(java.nio.Buffer arg0,int arg1,java.nio.ByteBuffer arg2)
  {
    printIndent();
    print(    "glObjectPtrLabel("+"<java.nio.Buffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg2+")");
    downstreamGLES3.glObjectPtrLabel(arg0,arg1,arg2);
    println("");
  }
  @Override
  public javax.media.opengl.GLBufferStorage mapBuffer(int arg0,int arg1)
  {
    printIndent();
    print(    "mapBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    javax.media.opengl.GLBufferStorage _res = downstreamGLES3.mapBuffer(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glDisableDriverControlQCOM(int arg0)
  {
    printIndent();
    print(    "glDisableDriverControlQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glDisableDriverControlQCOM(arg0);
    println("");
  }
  @Override
  public void glUniform4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print(    "glUniform4f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+", "+"<float> "+arg2+", "+"<float> "+arg3+", "+"<float> "+arg4+")");
    downstreamGLES3.glUniform4f(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform4i(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glUniform4i("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniform4i(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetActiveUniformBlockiv(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glGetActiveUniformBlockiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glGetActiveUniformBlockiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public int getDefaultReadBuffer()
  {
    return downstreamGLES3.getDefaultReadBuffer();
  }
  @Override
  public void glCoverageOperationNV(int arg0)
  {
    printIndent();
    print(    "glCoverageOperationNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glCoverageOperationNV(arg0);
    println("");
  }
  @Override
  public void glEnableDriverControlQCOM(int arg0)
  {
    printIndent();
    print(    "glEnableDriverControlQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glEnableDriverControlQCOM(arg0);
    println("");
  }
  @Override
  public void glInvalidateSubFramebuffer(int arg0,int arg1,java.nio.IntBuffer arg2,int arg3,int arg4,int arg5,int arg6)
  {
    printIndent();
    print(    "glInvalidateSubFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glInvalidateSubFramebuffer(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glBlitFramebufferNV(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
    printIndent();
    print(    "glBlitFramebufferNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+")");
    downstreamGLES3.glBlitFramebufferNV(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  @Override
  public void glBlitFramebufferANGLE(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
    printIndent();
    print(    "glBlitFramebufferANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+")");
    downstreamGLES3.glBlitFramebufferANGLE(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  @Override
  public void glDepthMask(boolean arg0)
  {
    printIndent();
    print(    "glDepthMask("+"<boolean> "+arg0+")");
    downstreamGLES3.glDepthMask(arg0);
    println("");
  }
  @Override
  public void glGetObjectPtrLabel(java.nio.Buffer arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetObjectPtrLabel("+"<java.nio.Buffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetObjectPtrLabel(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetBufferParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetBufferParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetBufferParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetActiveUniform(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print(    "glGetActiveUniform("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.IntBuffer> "+arg4+", "+"<java.nio.IntBuffer> "+arg5+", "+"<java.nio.ByteBuffer> "+arg6+")");
    downstreamGLES3.glGetActiveUniform(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glEGLImageTargetTexture2DOES(int arg0,long arg1)
  {
    printIndent();
    print(    "glEGLImageTargetTexture2DOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+")");
    downstreamGLES3.glEGLImageTargetTexture2DOES(arg0,arg1);
    println("");
  }
  @Override
  public void glWaitSync(long arg0,int arg1,long arg2)
  {
    printIndent();
    print(    "glWaitSync("+"<long> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<long> "+arg2+")");
    downstreamGLES3.glWaitSync(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glBindTexture(int arg0,int arg1)
  {
    printIndent();
    print(    "glBindTexture("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBindTexture(arg0,arg1);
    println("");
  }
  @Override
  public void glGetBufferParameteri64v(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
    printIndent();
    print(    "glGetBufferParameteri64v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.LongBuffer> "+arg2+")");
    downstreamGLES3.glGetBufferParameteri64v(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glClearColor(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print(    "glClearColor("+"<float> "+arg0+", "+"<float> "+arg1+", "+"<float> "+arg2+", "+"<float> "+arg3+")");
    downstreamGLES3.glClearColor(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetSamplerParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetSamplerParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetSamplerParameteriv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetActiveUniformBlockName(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.ByteBuffer arg4)
  {
    printIndent();
    print(    "glGetActiveUniformBlockName("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.ByteBuffer> "+arg4+")");
    downstreamGLES3.glGetActiveUniformBlockName(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDepthRangef(float arg0,float arg1)
  {
    printIndent();
    print(    "glDepthRangef("+"<float> "+arg0+", "+"<float> "+arg1+")");
    downstreamGLES3.glDepthRangef(arg0,arg1);
    println("");
  }
  @Override
  public void glGetShaderiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetShaderiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetShaderiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glBlitFramebuffer(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9)
  {
    printIndent();
    print(    "glBlitFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+")");
    downstreamGLES3.glBlitFramebuffer(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  @Override
  public void glPopDebugGroup()
  {
    printIndent();
    print(    "glPopDebugGroup("+")");
    downstreamGLES3.glPopDebugGroup();
    println("");
  }
  @Override
  public void glGenSamplers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenSamplers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenSamplers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glPushDebugGroup(int arg0,int arg1,int arg2,byte[] arg3,int arg4)
  {
    printIndent();
    print(    "glPushDebugGroup("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glPushDebugGroup(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetDriverControlsQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetDriverControlsQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetDriverControlsQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetDriverControlsQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetDriverControlsQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetDriverControlsQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform3fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDisable(int arg0)
  {
    printIndent();
    print(    "glDisable("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glDisable(arg0);
    println("");
  }
  @Override
  public void glEndTransformFeedback()
  {
    printIndent();
    print(    "glEndTransformFeedback("+")");
    downstreamGLES3.glEndTransformFeedback();
    println("");
  }
  @Override
  public void glGetQueryObjectuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetQueryObjectuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetQueryObjectuiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform1fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glUniform1fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glUniform1fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform4iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform4iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform4iv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttribI4ui(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glVertexAttribI4ui("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glVertexAttribI4ui(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public boolean glIsTexture(int arg0)
  {
    printIndent();
    print(    "glIsTexture("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsTexture(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public boolean glIsVBOElementArrayBound()
  {
    printIndent();
    print(    "glIsVBOElementArrayBound("+")");
    boolean _res = downstreamGLES3.glIsVBOElementArrayBound();
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetIntegeri_vEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetIntegeri_vEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetIntegeri_vEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glStencilFuncSeparate(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glStencilFuncSeparate("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glStencilFuncSeparate(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetInteger64v(int arg0,java.nio.LongBuffer arg1)
  {
    printIndent();
    print(    "glGetInteger64v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.LongBuffer> "+arg1+")");
    downstreamGLES3.glGetInteger64v(arg0,arg1);
    println("");
  }
  @Override
  public void glTexParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glTexParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glTexParameteriv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniformMatrix4x2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix4x2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix4x2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetProgramBinary(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print(    "glGetProgramBinary("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<java.nio.Buffer> "+arg6+")");
    downstreamGLES3.glGetProgramBinary(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glGetProgramBinary(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print(    "glGetProgramBinary("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.Buffer> "+arg4+")");
    downstreamGLES3.glGetProgramBinary(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform1fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform1fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform1fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glExtGetTexLevelParameterivQCOM(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glExtGetTexLevelParameterivQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glExtGetTexLevelParameterivQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDrawArraysInstanced(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glDrawArraysInstanced("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glDrawArraysInstanced(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glLinkProgram(int arg0)
  {
    printIndent();
    print(    "glLinkProgram("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glLinkProgram(arg0);
    println("");
  }
  @Override
  public void glClearBufferiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glClearBufferiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glClearBufferiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetQueryObjectivEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetQueryObjectivEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetQueryObjectivEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetActiveUniformsiv(int arg0,int arg1,java.nio.IntBuffer arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glGetActiveUniformsiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glGetActiveUniformsiv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetActiveUniformsiv(int arg0,int arg1,int[] arg2,int arg3,int arg4,int[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetActiveUniformsiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetActiveUniformsiv(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public boolean isPBOPackBound()
  {
    return downstreamGLES3.isPBOPackBound();
  }
  @Override
  public void glGetFramebufferAttachmentParameteriv(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glGetFramebufferAttachmentParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glGetFramebufferAttachmentParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniformMatrix4x2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix4x2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix4x2fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform1iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform1iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform1iv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean isVBOElementArrayBound()
  {
    return downstreamGLES3.isVBOElementArrayBound();
  }
  @Override
  public javax.media.opengl.GLBufferStorage getBufferStorage(int arg0)
  {
    return downstreamGLES3.getBufferStorage(arg0);
  }
  @Override
  public void glGetUniformuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetUniformuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetUniformuiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttrib3f(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print(    "glVertexAttrib3f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+", "+"<float> "+arg2+", "+"<float> "+arg3+")");
    downstreamGLES3.glVertexAttrib3f(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glClearDepthf(float arg0)
  {
    printIndent();
    print(    "glClearDepthf("+"<float> "+arg0+")");
    downstreamGLES3.glClearDepthf(arg0);
    println("");
  }
  @Override
  public void glBeginTransformFeedback(int arg0)
  {
    printIndent();
    print(    "glBeginTransformFeedback("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glBeginTransformFeedback(arg0);
    println("");
  }
  @Override
  public void glGetUniformIndices(int arg0,int arg1,java.lang.String[] arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glGetUniformIndices("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[Ljava.lang.String;>"+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glGetUniformIndices(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glProgramParameteri(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glProgramParameteri("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glProgramParameteri(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDeleteFramebuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteFramebuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteFramebuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glDisableVertexAttribArray(int arg0)
  {
    printIndent();
    print(    "glDisableVertexAttribArray("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glDisableVertexAttribArray(arg0);
    println("");
  }
  @Override
  public void glUniform3fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glUniform3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glUniform3fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDebugMessageControl(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4,boolean arg5)
  {
    printIndent();
    print(    "glDebugMessageControl("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg4+", "+"<boolean> "+arg5+")");
    downstreamGLES3.glDebugMessageControl(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glClearBufferfi(int arg0,int arg1,float arg2,int arg3)
  {
    printIndent();
    print(    "glClearBufferfi("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<float> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glClearBufferfi(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetQueryiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetQueryiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetQueryiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public boolean glExtIsProgramBinaryQCOM(int arg0)
  {
    printIndent();
    print(    "glExtIsProgramBinaryQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glExtIsProgramBinaryQCOM(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetRenderbufferParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetRenderbufferParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetRenderbufferParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public boolean glIsProgram(int arg0)
  {
    printIndent();
    print(    "glIsProgram("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsProgram(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glDeleteRenderbuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteRenderbuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteRenderbuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glRenderbufferStorageMultisampleANGLE(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glRenderbufferStorageMultisampleANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glRenderbufferStorageMultisampleANGLE(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glReadBufferIndexedEXT(int arg0,int arg1)
  {
    printIndent();
    print(    "glReadBufferIndexedEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glReadBufferIndexedEXT(arg0,arg1);
    println("");
  }
  @Override
  public void glExtGetRenderbuffersQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glExtGetRenderbuffersQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glExtGetRenderbuffersQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glExtGetRenderbuffersQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glExtGetRenderbuffersQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glExtGetRenderbuffersQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetSamplerParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetSamplerParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetSamplerParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetDriverControlStringQCOM(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetDriverControlStringQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetDriverControlStringQCOM(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glBindVertexArray(int arg0)
  {
    printIndent();
    print(    "glBindVertexArray("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glBindVertexArray(arg0);
    println("");
  }
  @Override
  public void glGetTransformFeedbackVarying(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print(    "glGetTransformFeedbackVarying("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.IntBuffer> "+arg4+", "+"<java.nio.IntBuffer> "+arg5+", "+"<java.nio.ByteBuffer> "+arg6+")");
    downstreamGLES3.glGetTransformFeedbackVarying(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public java.nio.ByteBuffer glMapBuffer(int arg0,int arg1)
  {
    printIndent();
    print(    "glMapBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    java.nio.ByteBuffer _res = downstreamGLES3.glMapBuffer(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glVertexAttribPointer(javax.media.opengl.GLArrayData arg0)
  {
    printIndent();
    print(    "glVertexAttribPointer("+"<javax.media.opengl.GLArrayData> "+arg0+")");
    downstreamGLES3.glVertexAttribPointer(arg0);
    println("");
  }
  @Override
  public void glGetSamplerParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glGetSamplerParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glGetSamplerParameterfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glEndPerfMonitorAMD(int arg0)
  {
    printIndent();
    print(    "glEndPerfMonitorAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glEndPerfMonitorAMD(arg0);
    println("");
  }
  @Override
  public void glFramebufferTextureLayer(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glFramebufferTextureLayer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glFramebufferTextureLayer(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDeleteQueries(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteQueries("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteQueries(arg0,arg1);
    println("");
  }
  @Override
  public void glGetQueryObjectui64vEXT(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
    printIndent();
    print(    "glGetQueryObjectui64vEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.LongBuffer> "+arg2+")");
    downstreamGLES3.glGetQueryObjectui64vEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public int glGetDebugMessageLog(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5,int[] arg6,int arg7,int[] arg8,int arg9,int[] arg10,int arg11,byte[] arg12,int arg13)
  {
    printIndent();
    print(    "glGetDebugMessageLog("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg11).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg13).toUpperCase()+")");
    int _res = downstreamGLES3.glGetDebugMessageLog(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10,arg11,arg12,arg13);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glReadBufferNV(int arg0)
  {
    printIndent();
    print(    "glReadBufferNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glReadBufferNV(arg0);
    println("");
  }
  @Override
  public void glGenQueries(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenQueries("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenQueries(arg0,arg1);
    println("");
  }
  @Override
  public void glUniformBlockBinding(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glUniformBlockBinding("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glUniformBlockBinding(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttrib1f(int arg0,float arg1)
  {
    printIndent();
    print(    "glVertexAttrib1f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+")");
    downstreamGLES3.glVertexAttrib1f(arg0,arg1);
    println("");
  }
  @Override
  public void glFlush()
  {
    printIndent();
    print(    "glFlush("+")");
    downstreamGLES3.glFlush();
    println("");
  }
  @Override
  public void glGetObjectPtrLabel(java.nio.Buffer arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetObjectPtrLabel("+"<java.nio.Buffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetObjectPtrLabel(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glGetActiveUniform(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print(    "glGetActiveUniform("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg10).toUpperCase()+")");
    downstreamGLES3.glGetActiveUniform(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public void glGetPerfMonitorCounterInfoAMD(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print(    "glGetPerfMonitorCounterInfoAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.Buffer> "+arg3+")");
    downstreamGLES3.glGetPerfMonitorCounterInfoAMD(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDeleteSamplers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteSamplers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteSamplers(arg0,arg1);
    println("");
  }
  @Override
  public void glGetVertexAttribIiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetVertexAttribIiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetVertexAttribIiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttribPointer(int arg0,int arg1,int arg2,boolean arg3,int arg4,long arg5)
  {
    printIndent();
    print(    "glVertexAttribPointer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<boolean> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<long> "+arg5+")");
    downstreamGLES3.glVertexAttribPointer(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public long glFenceSync(int arg0,int arg1)
  {
    printIndent();
    print(    "glFenceSync("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    long _res = downstreamGLES3.glFenceSync(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetVertexAttribiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetVertexAttribiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetVertexAttribiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public javax.media.opengl.GLContext getContext()
  {
    return downstreamGLES3.getContext();
  }
  @Override
  public void glExtGetTexturesQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glExtGetTexturesQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glExtGetTexturesQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glExtGetTexturesQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glExtGetTexturesQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glExtGetTexturesQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glSamplerParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glSamplerParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glSamplerParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glAlphaFuncQCOM(int arg0,float arg1)
  {
    printIndent();
    print(    "glAlphaFuncQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+")");
    downstreamGLES3.glAlphaFuncQCOM(arg0,arg1);
    println("");
  }
  @Override
  public void glReadPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,java.nio.Buffer arg6)
  {
    printIndent();
    print(    "glReadPixels("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<java.nio.Buffer> "+arg6+")");
    downstreamGLES3.glReadPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glFramebufferTexture3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print(    "glFramebufferTexture3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glFramebufferTexture3D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glEndQuery(int arg0)
  {
    printIndent();
    print(    "glEndQuery("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glEndQuery(arg0);
    println("");
  }
  @Override
  public void glUniformMatrix3x2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix3x2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix3x2fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDrawElementsInstancedNV(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
    printIndent();
    print(    "glDrawElementsInstancedNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.Buffer> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glDrawElementsInstancedNV(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform2fv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glSamplerParameterf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print(    "glSamplerParameterf("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<float> "+arg2+")");
    downstreamGLES3.glSamplerParameterf(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetInteger64v(int arg0,long[] arg1,int arg2)
  {
    printIndent();
    print(    "glGetInteger64v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[J>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGetInteger64v(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glReadPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,long arg6)
  {
    printIndent();
    print(    "glReadPixels("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<long> "+arg6+")");
    downstreamGLES3.glReadPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glGetActiveUniformBlockName(int arg0,int arg1,int arg2,int[] arg3,int arg4,byte[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetActiveUniformBlockName("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetActiveUniformBlockName(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glSamplerParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glSamplerParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glSamplerParameterfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public javax.media.opengl.GLBufferStorage mapBufferRange(int arg0,long arg1,long arg2,int arg3)
  {
    printIndent();
    print(    "mapBufferRange("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+", "+"<long> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    javax.media.opengl.GLBufferStorage _res = downstreamGLES3.mapBufferRange(arg0,arg1,arg2,arg3);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glPushGroupMarkerEXT(int arg0,java.nio.ByteBuffer arg1)
  {
    printIndent();
    print(    "glPushGroupMarkerEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg1+")");
    downstreamGLES3.glPushGroupMarkerEXT(arg0,arg1);
    println("");
  }
  @Override
  public void glUniformMatrix3x2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix3x2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix3x2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDrawBuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDrawBuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDrawBuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform2fv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glUniform2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glUniform2fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glShaderBinary(int arg0,java.nio.IntBuffer arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
    printIndent();
    print(    "glShaderBinary("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.Buffer> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glShaderBinary(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glScissor(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glScissor("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glScissor(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glSamplerParameteri(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glSamplerParameteri("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glSamplerParameteri(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDrawBuffersIndexedEXT(int arg0,int[] arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glDrawBuffersIndexedEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glDrawBuffersIndexedEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glVertexAttribPointer(int arg0,int arg1,int arg2,boolean arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print(    "glVertexAttribPointer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<boolean> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<java.nio.Buffer> "+arg5+")");
    downstreamGLES3.glVertexAttribPointer(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glDrawArraysInstancedNV(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glDrawArraysInstancedNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glDrawArraysInstancedNV(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDrawBuffersIndexedEXT(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glDrawBuffersIndexedEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glDrawBuffersIndexedEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTextureStorage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print(    "glTextureStorage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glTextureStorage2D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public javax.media.opengl.GL getRootGL()
  {
    return downstreamGLES3.getRootGL();
  }
  @Override
  public void glGetProgramiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetProgramiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetProgramiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttribDivisorANGLE(int arg0,int arg1)
  {
    printIndent();
    print(    "glVertexAttribDivisorANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glVertexAttribDivisorANGLE(arg0,arg1);
    println("");
  }
  @Override
  public void glSelectPerfMonitorCountersAMD(int arg0,boolean arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glSelectPerfMonitorCountersAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<boolean> "+arg1+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glSelectPerfMonitorCountersAMD(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDeleteVertexArrays(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteVertexArrays("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteVertexArrays(arg0,arg1);
    println("");
  }
  @Override
  public void glDeleteTransformFeedbacks(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteTransformFeedbacks("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteTransformFeedbacks(arg0,arg1);
    println("");
  }
  @Override
  public void glReadBuffer(int arg0)
  {
    printIndent();
    print(    "glReadBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glReadBuffer(arg0);
    println("");
  }
  @Override
  public void glShaderSource(int arg0,int arg1,java.lang.String[] arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glShaderSource("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[Ljava.lang.String;>"+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glShaderSource(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDrawArraysInstancedANGLE(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glDrawArraysInstancedANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glDrawArraysInstancedANGLE(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttrib3fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print(    "glVertexAttrib3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg1+")");
    downstreamGLES3.glVertexAttrib3fv(arg0,arg1);
    println("");
  }
  @Override
  public void glVertexAttrib1fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print(    "glVertexAttrib1fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg1+")");
    downstreamGLES3.glVertexAttrib1fv(arg0,arg1);
    println("");
  }
  @Override
  public void glGenFencesNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenFencesNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenFencesNV(arg0,arg1);
    println("");
  }
  @Override
  public void glUniformMatrix2fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix2fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public boolean isExtensionAvailable(java.lang.String arg0)
  {
    return downstreamGLES3.isExtensionAvailable(arg0);
  }
  @Override
  public void glGetQueryObjectui64vEXT(int arg0,int arg1,long[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetQueryObjectui64vEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[J>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetQueryObjectui64vEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glExtGetBufferPointervQCOM(int arg0,com.jogamp.common.nio.PointerBuffer arg1)
  {
    printIndent();
    print(    "glExtGetBufferPointervQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<com.jogamp.common.nio.PointerBuffer> "+arg1+")");
    downstreamGLES3.glExtGetBufferPointervQCOM(arg0,arg1);
    println("");
  }
  @Override
  public boolean glIsQuery(int arg0)
  {
    printIndent();
    print(    "glIsQuery("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsQuery(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glValidateProgram(int arg0)
  {
    printIndent();
    print(    "glValidateProgram("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glValidateProgram(arg0);
    println("");
  }
  @Override
  public void glBeginQuery(int arg0,int arg1)
  {
    printIndent();
    print(    "glBeginQuery("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBeginQuery(arg0,arg1);
    println("");
  }
  @Override
  public void glSampleCoverage(float arg0,boolean arg1)
  {
    printIndent();
    print(    "glSampleCoverage("+"<float> "+arg0+", "+"<boolean> "+arg1+")");
    downstreamGLES3.glSampleCoverage(arg0,arg1);
    println("");
  }
  @Override
  public void glUniform2uiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform2uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform2uiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean glIsTransformFeedback(int arg0)
  {
    printIndent();
    print(    "glIsTransformFeedback("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsTransformFeedback(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glUniformMatrix4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix4fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print(    "glTexImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<java.nio.Buffer> "+arg8+")");
    downstreamGLES3.glTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public java.nio.ByteBuffer glMapBufferRange(int arg0,long arg1,long arg2,int arg3)
  {
    printIndent();
    print(    "glMapBufferRange("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+", "+"<long> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    java.nio.ByteBuffer _res = downstreamGLES3.glMapBufferRange(arg0,arg1,arg2,arg3);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetVertexAttribfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetVertexAttribfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetVertexAttribfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetShaderInfoLog(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetShaderInfoLog("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetShaderInfoLog(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glGetFloatv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print(    "glGetFloatv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGetFloatv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glBindBufferRange(int arg0,int arg1,int arg2,long arg3,long arg4)
  {
    printIndent();
    print(    "glBindBufferRange("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<long> "+arg3+", "+"<long> "+arg4+")");
    downstreamGLES3.glBindBufferRange(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glPauseTransformFeedback()
  {
    printIndent();
    print(    "glPauseTransformFeedback("+")");
    downstreamGLES3.glPauseTransformFeedback();
    println("");
  }
  @Override
  public boolean hasGLSL()
  {
    return downstreamGLES3.hasGLSL();
  }
  @Override
  public void glGetnUniformiv(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glGetnUniformiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glGetnUniformiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print(    "glTexImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<long> "+arg8+")");
    downstreamGLES3.glTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public void glUniformMatrix4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetVertexAttribfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glGetVertexAttribfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glGetVertexAttribfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUseProgram(int arg0)
  {
    printIndent();
    print(    "glUseProgram("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glUseProgram(arg0);
    println("");
  }
  @Override
  public void glGenerateMipmap(int arg0)
  {
    printIndent();
    print(    "glGenerateMipmap("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glGenerateMipmap(arg0);
    println("");
  }
  @Override
  public void glGetFloatv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print(    "glGetFloatv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg1+")");
    downstreamGLES3.glGetFloatv(arg0,arg1);
    println("");
  }
  @Override
  public void glGetShaderPrecisionFormat(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetShaderPrecisionFormat("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetShaderPrecisionFormat(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glGetShaderPrecisionFormat(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glGetShaderPrecisionFormat("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glGetShaderPrecisionFormat(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDeleteBuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteBuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteBuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDetachShader(int arg0,int arg1)
  {
    printIndent();
    print(    "glDetachShader("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glDetachShader(arg0,arg1);
    println("");
  }
  @Override
  public void glVertexAttrib3fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print(    "glVertexAttrib3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glVertexAttrib3fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttrib1fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print(    "glVertexAttrib1fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glVertexAttrib1fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGenPerfMonitorsAMD(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenPerfMonitorsAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenPerfMonitorsAMD(arg0,arg1);
    println("");
  }
  @Override
  public void glDeleteShader(int arg0)
  {
    printIndent();
    print(    "glDeleteShader("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glDeleteShader(arg0);
    println("");
  }
  @Override
  public void glGetFenceivNV(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetFenceivNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetFenceivNV(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniformMatrix2fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix2fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glEnable(int arg0)
  {
    printIndent();
    print(    "glEnable("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glEnable(arg0);
    println("");
  }
  @Override
  public void glSetFenceNV(int arg0,int arg1)
  {
    printIndent();
    print(    "glSetFenceNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glSetFenceNV(arg0,arg1);
    println("");
  }
  @Override
  public void glRenderbufferStorageMultisampleIMG(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glRenderbufferStorageMultisampleIMG("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glRenderbufferStorageMultisampleIMG(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glFinish()
  {
    printIndent();
    print(    "glFinish("+")");
    downstreamGLES3.glFinish();
    println("");
  }
  @Override
  public void glGenTransformFeedbacks(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenTransformFeedbacks("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenTransformFeedbacks(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glAttachShader(int arg0,int arg1)
  {
    printIndent();
    print(    "glAttachShader("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glAttachShader(arg0,arg1);
    println("");
  }
  @Override
  public void glFramebufferTexture2D(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glFramebufferTexture2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glFramebufferTexture2D(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glBindAttribLocation(int arg0,int arg1,java.lang.String arg2)
  {
    printIndent();
    print(    "glBindAttribLocation("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.lang.String> "+arg2+")");
    downstreamGLES3.glBindAttribLocation(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetTexParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetTexParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetTexParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetTransformFeedbackVarying(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print(    "glGetTransformFeedbackVarying("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg10).toUpperCase()+")");
    downstreamGLES3.glGetTransformFeedbackVarying(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public void glActiveTexture(int arg0)
  {
    printIndent();
    print(    "glActiveTexture("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glActiveTexture(arg0);
    println("");
  }
  @Override
  public void glGetnUniformfv(int arg0,int arg1,int arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glGetnUniformfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glGetnUniformfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniform3iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform3iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform3iv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetPerfMonitorCountersAMD(int arg0,int[] arg1,int arg2,int[] arg3,int arg4,int arg5,int[] arg6,int arg7)
  {
    printIndent();
    print(    "glGetPerfMonitorCountersAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+")");
    downstreamGLES3.glGetPerfMonitorCountersAMD(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glVertexAttribDivisorNV(int arg0,int arg1)
  {
    printIndent();
    print(    "glVertexAttribDivisorNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glVertexAttribDivisorNV(arg0,arg1);
    println("");
  }
  @Override
  public void glLabelObjectEXT(int arg0,int arg1,int arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glLabelObjectEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glLabelObjectEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetDriverControlStringQCOM(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetDriverControlStringQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetDriverControlStringQCOM(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glUniform2iv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform2iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform2iv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glBindBufferBase(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glBindBufferBase("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glBindBufferBase(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDeleteSync(long arg0)
  {
    printIndent();
    print(    "glDeleteSync("+"<long> "+arg0+")");
    downstreamGLES3.glDeleteSync(arg0);
    println("");
  }
  @Override
  public void glGenBuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenBuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenBuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTextureStorage1D(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glTextureStorage1D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glTextureStorage1D(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glTransformFeedbackVaryings(int arg0,int arg1,java.lang.String[] arg2,int arg3)
  {
    printIndent();
    print(    "glTransformFeedbackVaryings("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[Ljava.lang.String;>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glTransformFeedbackVaryings(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetnUniformfv(int arg0,int arg1,int arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetnUniformfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetnUniformfv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDeletePerfMonitorsAMD(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeletePerfMonitorsAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeletePerfMonitorsAMD(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glBufferData(int arg0,long arg1,java.nio.Buffer arg2,int arg3)
  {
    printIndent();
    print(    "glBufferData("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+", "+"<java.nio.Buffer> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glBufferData(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDeleteTextures(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteTextures("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteTextures(arg0,arg1);
    println("");
  }
  @Override
  public void glGenFramebuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenFramebuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenFramebuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glGenRenderbuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenRenderbuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenRenderbuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttribI4iv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glVertexAttribI4iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glVertexAttribI4iv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glEnableVertexAttribArray(int arg0)
  {
    printIndent();
    print(    "glEnableVertexAttribArray("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glEnableVertexAttribArray(arg0);
    println("");
  }
  @Override
  public void glClearStencil(int arg0)
  {
    printIndent();
    print(    "glClearStencil("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glClearStencil(arg0);
    println("");
  }
  @Override
  public void glClearBufferuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glClearBufferuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glClearBufferuiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDiscardFramebufferEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glDiscardFramebufferEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glDiscardFramebufferEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glViewport(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glViewport("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glViewport(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glFlushMappedBufferRange(int arg0,long arg1,long arg2)
  {
    printIndent();
    print(    "glFlushMappedBufferRange("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+", "+"<long> "+arg2+")");
    downstreamGLES3.glFlushMappedBufferRange(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetIntegerv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGetIntegerv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGetIntegerv(arg0,arg1);
    println("");
  }
  @Override
  public int glGetDebugMessageLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.IntBuffer arg6,java.nio.ByteBuffer arg7)
  {
    printIndent();
    print(    "glGetDebugMessageLog("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.IntBuffer> "+arg4+", "+"<java.nio.IntBuffer> "+arg5+", "+"<java.nio.IntBuffer> "+arg6+", "+"<java.nio.ByteBuffer> "+arg7+")");
    int _res = downstreamGLES3.glGetDebugMessageLog(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println(" = "+_res);
    return _res;
  }
  @Override
  public int glCheckFramebufferStatus(int arg0)
  {
    printIndent();
    print(    "glCheckFramebufferStatus("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    int _res = downstreamGLES3.glCheckFramebufferStatus(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glUniform4uiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform4uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform4uiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean isPBOUnpackBound()
  {
    return downstreamGLES3.isPBOUnpackBound();
  }
  @Override
  public void glVertexAttrib4f(int arg0,float arg1,float arg2,float arg3,float arg4)
  {
    printIndent();
    print(    "glVertexAttrib4f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+", "+"<float> "+arg2+", "+"<float> "+arg3+", "+"<float> "+arg4+")");
    downstreamGLES3.glVertexAttrib4f(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGenVertexArraysOES(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenVertexArraysOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenVertexArraysOES(arg0,arg1);
    println("");
  }
  @Override
  public boolean glIsRenderbuffer(int arg0)
  {
    printIndent();
    print(    "glIsRenderbuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsRenderbuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,java.nio.Buffer arg9)
  {
    printIndent();
    print(    "glTexImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<java.nio.Buffer> "+arg9+")");
    downstreamGLES3.glTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  @Override
  public void glGetIntegeri_v(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetIntegeri_v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetIntegeri_v(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetSynciv(long arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glGetSynciv("+"<long> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glGetSynciv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetSynciv(long arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetSynciv("+"<long> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetSynciv(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glGetUniformfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glGetUniformfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glGetUniformfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glFinishFenceNV(int arg0)
  {
    printIndent();
    print(    "glFinishFenceNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glFinishFenceNV(arg0);
    println("");
  }
  @Override
  public void glUniform1uiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform1uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform1uiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void setSwapInterval(int arg0)
  {
    downstreamGLES3.setSwapInterval(arg0);
  }
  @Override
  public boolean glIsPBOUnpackBound()
  {
    printIndent();
    print(    "glIsPBOUnpackBound("+")");
    boolean _res = downstreamGLES3.glIsPBOUnpackBound();
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetInternalformativ(int arg0,int arg1,int arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glGetInternalformativ("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glGetInternalformativ(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public boolean glIsBuffer(int arg0)
  {
    printIndent();
    print(    "glIsBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsBuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glUniformMatrix3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix3fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glVertexAttrib2fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print(    "glVertexAttrib2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg1+")");
    downstreamGLES3.glVertexAttrib2fv(arg0,arg1);
    println("");
  }
  @Override
  public void glBlendEquation(int arg0)
  {
    printIndent();
    print(    "glBlendEquation("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glBlendEquation(arg0);
    println("");
  }
  @Override
  public void glDeleteVertexArraysOES(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteVertexArraysOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteVertexArraysOES(arg0,arg1);
    println("");
  }
  @Override
  public void glInvalidateFramebuffer(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glInvalidateFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glInvalidateFramebuffer(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetBufferParameteri64v(int arg0,int arg1,long[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetBufferParameteri64v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[J>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetBufferParameteri64v(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniformMatrix3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttrib2fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print(    "glVertexAttrib2fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glVertexAttrib2fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTextureStorage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6)
  {
    printIndent();
    print(    "glTextureStorage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glTextureStorage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public boolean glIsSampler(int arg0)
  {
    printIndent();
    print(    "glIsSampler("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsSampler(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGenTextures(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenTextures("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenTextures(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttribI4uiv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glVertexAttribI4uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glVertexAttribI4uiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetUniformiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetUniformiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetUniformiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGenVertexArrays(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenVertexArrays("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenVertexArrays(arg0,arg1);
    println("");
  }
  @Override
  public void glGetUniformfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetUniformfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetUniformfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glPopGroupMarkerEXT()
  {
    printIndent();
    print(    "glPopGroupMarkerEXT("+")");
    downstreamGLES3.glPopGroupMarkerEXT();
    println("");
  }
  @Override
  public void glDeleteFencesNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteFencesNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteFencesNV(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,long arg9)
  {
    printIndent();
    print(    "glTexImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<long> "+arg9+")");
    downstreamGLES3.glTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9);
    println("");
  }
  @Override
  public void glCullFace(int arg0)
  {
    printIndent();
    print(    "glCullFace("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glCullFace(arg0);
    println("");
  }
  @Override
  public void glUniform3uiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform3uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform3uiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetVertexAttribIuiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetVertexAttribIuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetVertexAttribIuiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glStencilMaskSeparate(int arg0,int arg1)
  {
    printIndent();
    print(    "glStencilMaskSeparate("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glStencilMaskSeparate(arg0,arg1);
    println("");
  }
  @Override
  public void glDrawElements(int arg0,int arg1,int arg2,long arg3)
  {
    printIndent();
    print(    "glDrawElements("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<long> "+arg3+")");
    downstreamGLES3.glDrawElements(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetTranslatedShaderSourceANGLE(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetTranslatedShaderSourceANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetTranslatedShaderSourceANGLE(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetShaderSource(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetShaderSource("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetShaderSource(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print(    "glTexSubImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<java.nio.Buffer> "+arg8+")");
    downstreamGLES3.glTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public boolean glUnmapBuffer(int arg0)
  {
    printIndent();
    print(    "glUnmapBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glUnmapBuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetPerfMonitorCounterStringAMD(int arg0,int arg1,int arg2,int[] arg3,int arg4,byte[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetPerfMonitorCounterStringAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetPerfMonitorCounterStringAMD(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glCopyTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
    printIndent();
    print(    "glCopyTexImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+")");
    downstreamGLES3.glCopyTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glRenderbufferStorageMultisampleAPPLE(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glRenderbufferStorageMultisampleAPPLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glRenderbufferStorageMultisampleAPPLE(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetActiveUniformBlockiv(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetActiveUniformBlockiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetActiveUniformBlockiv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetPerfMonitorGroupStringAMD(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetPerfMonitorGroupStringAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetPerfMonitorGroupStringAMD(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public boolean glIsVertexArray(int arg0)
  {
    printIndent();
    print(    "glIsVertexArray("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsVertexArray(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glExtGetProgramBinarySourceQCOM(int arg0,int arg1,java.nio.ByteBuffer arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glExtGetProgramBinarySourceQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg2+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glExtGetProgramBinarySourceQCOM(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttribDivisor(int arg0,int arg1)
  {
    printIndent();
    print(    "glVertexAttribDivisor("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glVertexAttribDivisor(arg0,arg1);
    println("");
  }
  @Override
  public int glCreateShader(int arg0)
  {
    printIndent();
    print(    "glCreateShader("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    int _res = downstreamGLES3.glCreateShader(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glBeginPerfMonitorAMD(int arg0)
  {
    printIndent();
    print(    "glBeginPerfMonitorAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glBeginPerfMonitorAMD(arg0);
    println("");
  }
  @Override
  public void glGetProgramInfoLog(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetProgramInfoLog("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetProgramInfoLog(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glBlendFunc(int arg0,int arg1)
  {
    printIndent();
    print(    "glBlendFunc("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBlendFunc(arg0,arg1);
    println("");
  }
  @Override
  public void glSamplerParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glSamplerParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glSamplerParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public boolean glTestFenceNV(int arg0)
  {
    printIndent();
    print(    "glTestFenceNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glTestFenceNV(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glDrawElements(int arg0,int arg1,int arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print(    "glDrawElements("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.Buffer> "+arg3+")");
    downstreamGLES3.glDrawElements(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public java.lang.String glGetString(int arg0)
  {
    printIndent();
    print(    "glGetString("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    java.lang.String _res = downstreamGLES3.glGetString(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glStencilFunc(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glStencilFunc("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glStencilFunc(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glReleaseShaderCompiler()
  {
    printIndent();
    print(    "glReleaseShaderCompiler("+")");
    downstreamGLES3.glReleaseShaderCompiler();
    println("");
  }
  @Override
  public void glTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print(    "glTexSubImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<long> "+arg8+")");
    downstreamGLES3.glTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public boolean isFunctionAvailable(java.lang.String arg0)
  {
    return downstreamGLES3.isFunctionAvailable(arg0);
  }
  @Override
  public void glBindTransformFeedback(int arg0,int arg1)
  {
    printIndent();
    print(    "glBindTransformFeedback("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBindTransformFeedback(arg0,arg1);
    println("");
  }
  @Override
  public void glReadnPixels(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,java.nio.Buffer arg7)
  {
    printIndent();
    print(    "glReadnPixels("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<java.nio.Buffer> "+arg7+")");
    downstreamGLES3.glReadnPixels(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glGetActiveAttrib(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6,int[] arg7,int arg8,byte[] arg9,int arg10)
  {
    printIndent();
    print(    "glGetActiveAttrib("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg10).toUpperCase()+")");
    downstreamGLES3.glGetActiveAttrib(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public void glGetSamplerParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetSamplerParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetSamplerParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetShaderiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetShaderiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetShaderiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glPushGroupMarkerEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print(    "glPushGroupMarkerEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glPushGroupMarkerEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public int getBoundBuffer(int arg0)
  {
    return downstreamGLES3.getBoundBuffer(arg0);
  }
  @Override
  public void glBufferSubData(int arg0,long arg1,long arg2,java.nio.Buffer arg3)
  {
    printIndent();
    print(    "glBufferSubData("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+", "+"<long> "+arg2+", "+"<java.nio.Buffer> "+arg3+")");
    downstreamGLES3.glBufferSubData(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniform3ui(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glUniform3ui("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform3ui(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGenSamplers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenSamplers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenSamplers(arg0,arg1);
    println("");
  }
  @Override
  public void glExtTexObjectStateOverrideiQCOM(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glExtTexObjectStateOverrideiQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glExtTexObjectStateOverrideiQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glColorMask(boolean arg0,boolean arg1,boolean arg2,boolean arg3)
  {
    printIndent();
    print(    "glColorMask("+"<boolean> "+arg0+", "+"<boolean> "+arg1+", "+"<boolean> "+arg2+", "+"<boolean> "+arg3+")");
    downstreamGLES3.glColorMask(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,long arg10)
  {
    printIndent();
    print(    "glTexSubImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+", "+"<long> "+arg10+")");
    downstreamGLES3.glTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public int glGetAttribLocation(int arg0,java.lang.String arg1)
  {
    printIndent();
    print(    "glGetAttribLocation("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.lang.String> "+arg1+")");
    int _res = downstreamGLES3.glGetAttribLocation(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetObjectLabel(int arg0,int arg1,int arg2,int[] arg3,int arg4,byte[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetObjectLabel("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetObjectLabel(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glInvalidateSubFramebuffer(int arg0,int arg1,int[] arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
    printIndent();
    print(    "glInvalidateSubFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+")");
    downstreamGLES3.glInvalidateSubFramebuffer(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glGetBufferParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetBufferParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetBufferParameteriv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
    printIndent();
    print(    "glTexSubImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+", "+"<java.nio.Buffer> "+arg10+")");
    downstreamGLES3.glTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public int getDefaultDrawFramebuffer()
  {
    return downstreamGLES3.getDefaultDrawFramebuffer();
  }
  @Override
  public void glUniform1ui(int arg0,int arg1)
  {
    printIndent();
    print(    "glUniform1ui("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glUniform1ui(arg0,arg1);
    println("");
  }
  @Override
  public java.lang.Object getExtension(java.lang.String arg0)
  {
    return downstreamGLES3.getExtension(arg0);
  }
  @Override
  public void glFrontFace(int arg0)
  {
    printIndent();
    print(    "glFrontFace("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glFrontFace(arg0);
    println("");
  }
  @Override
  public void glExtGetTexLevelParameterivQCOM(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print(    "glExtGetTexLevelParameterivQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glExtGetTexLevelParameterivQCOM(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glBlendColor(float arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print(    "glBlendColor("+"<float> "+arg0+", "+"<float> "+arg1+", "+"<float> "+arg2+", "+"<float> "+arg3+")");
    downstreamGLES3.glBlendColor(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glClearBufferiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glClearBufferiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glClearBufferiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetQueryObjectivEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetQueryObjectivEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetQueryObjectivEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean glIsPBOPackBound()
  {
    printIndent();
    print(    "glIsPBOPackBound("+")");
    boolean _res = downstreamGLES3.glIsPBOPackBound();
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glVertexAttribIPointer(int arg0,int arg1,int arg2,int arg3,java.nio.Buffer arg4)
  {
    printIndent();
    print(    "glVertexAttribIPointer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.Buffer> "+arg4+")");
    downstreamGLES3.glVertexAttribIPointer(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetQueryObjecti64vEXT(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
    printIndent();
    print(    "glGetQueryObjecti64vEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.LongBuffer> "+arg2+")");
    downstreamGLES3.glGetQueryObjecti64vEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glVertexAttrib4fv(int arg0,float[] arg1,int arg2)
  {
    printIndent();
    print(    "glVertexAttrib4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glVertexAttrib4fv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetFramebufferAttachmentParameteriv(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetFramebufferAttachmentParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetFramebufferAttachmentParameteriv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glStartTilingQCOM(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glStartTilingQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glStartTilingQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform1iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform1iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform1iv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetUniformuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetUniformuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetUniformuiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetUniformIndices(int arg0,int arg1,java.lang.String[] arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetUniformIndices("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[Ljava.lang.String;>"+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetUniformIndices(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glProgramBinary(int arg0,int arg1,java.nio.Buffer arg2,int arg3)
  {
    printIndent();
    print(    "glProgramBinary("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.Buffer> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glProgramBinary(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDeleteFramebuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteFramebuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteFramebuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean glIsEnabled(int arg0)
  {
    printIndent();
    print(    "glIsEnabled("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsEnabled(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glDebugMessageControl(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5,boolean arg6)
  {
    printIndent();
    print(    "glDebugMessageControl("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<boolean> "+arg6+")");
    downstreamGLES3.glDebugMessageControl(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glGetQueryiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetQueryiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetQueryiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean hasBasicFBOSupport()
  {
    return downstreamGLES3.hasBasicFBOSupport();
  }
  @Override
  public void glFramebufferRenderbuffer(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glFramebufferRenderbuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glFramebufferRenderbuffer(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetQueryObjectuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetQueryObjectuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetQueryObjectuiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniform4iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform4iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform4iv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public java.lang.Object getPlatformGLExtensions()
  {
    return downstreamGLES3.getPlatformGLExtensions();
  }
  @Override
  public void glVertexAttribIPointer(int arg0,int arg1,int arg2,int arg3,long arg4)
  {
    printIndent();
    print(    "glVertexAttribIPointer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<long> "+arg4+")");
    downstreamGLES3.glVertexAttribIPointer(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetIntegeri_vEXT(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetIntegeri_vEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetIntegeri_vEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glVertexAttrib4fv(int arg0,java.nio.FloatBuffer arg1)
  {
    printIndent();
    print(    "glVertexAttrib4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg1+")");
    downstreamGLES3.glVertexAttrib4fv(arg0,arg1);
    println("");
  }
  @Override
  public void glTexParameteriv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glTexParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glTexParameteriv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glFramebufferTexture2DMultisampleEXT(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print(    "glFramebufferTexture2DMultisampleEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glFramebufferTexture2DMultisampleEXT(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glFramebufferTexture2DMultisampleIMG(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print(    "glFramebufferTexture2DMultisampleIMG("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glFramebufferTexture2DMultisampleIMG(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glBlendEquationSeparate(int arg0,int arg1)
  {
    printIndent();
    print(    "glBlendEquationSeparate("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBlendEquationSeparate(arg0,arg1);
    println("");
  }
  @Override
  public void glGetInteger64i_v(int arg0,int arg1,java.nio.LongBuffer arg2)
  {
    printIndent();
    print(    "glGetInteger64i_v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.LongBuffer> "+arg2+")");
    downstreamGLES3.glGetInteger64i_v(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDeleteQueries(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteQueries("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteQueries(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDrawArrays(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glDrawArrays("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDrawArrays(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glBindBuffer(int arg0,int arg1)
  {
    printIndent();
    print(    "glBindBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBindBuffer(arg0,arg1);
    println("");
  }
  @Override
  public void glGetObjectLabelEXT(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.ByteBuffer arg4)
  {
    printIndent();
    print(    "glGetObjectLabelEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.ByteBuffer> "+arg4+")");
    downstreamGLES3.glGetObjectLabelEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public java.lang.String glGetStringi(int arg0,int arg1)
  {
    printIndent();
    print(    "glGetStringi("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    java.lang.String _res = downstreamGLES3.glGetStringi(arg0,arg1);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glStencilMask(int arg0)
  {
    printIndent();
    print(    "glStencilMask("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glStencilMask(arg0);
    println("");
  }
  @Override
  public void glGenQueries(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenQueries("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenQueries(arg0,arg1,arg2);
    println("");
  }
  @Override
  public int glGetError()
  {
    printIndent();
    print(    "glGetError("+")");
    int _res = downstreamGLES3.glGetError();
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glBlendFuncSeparate(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glBlendFuncSeparate("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glBlendFuncSeparate(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetRenderbufferParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetRenderbufferParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetRenderbufferParameteriv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glResolveMultisampleFramebufferAPPLE()
  {
    printIndent();
    print(    "glResolveMultisampleFramebufferAPPLE("+")");
    downstreamGLES3.glResolveMultisampleFramebufferAPPLE();
    println("");
  }
  @Override
  public boolean glIsFramebuffer(int arg0)
  {
    printIndent();
    print(    "glIsFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsFramebuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glClear(int arg0)
  {
    printIndent();
    print(    "glClear("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glClear(arg0);
    println("");
  }
  @Override
  public void glUniform2ui(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glUniform2ui("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glUniform2ui(arg0,arg1,arg2);
    println("");
  }
  @Override
  public int glClientWaitSync(long arg0,int arg1,long arg2)
  {
    printIndent();
    print(    "glClientWaitSync("+"<long> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<long> "+arg2+")");
    int _res = downstreamGLES3.glClientWaitSync(arg0,arg1,arg2);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glDeleteRenderbuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteRenderbuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteRenderbuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glLabelObjectEXT(int arg0,int arg1,int arg2,byte[] arg3,int arg4)
  {
    printIndent();
    print(    "glLabelObjectEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glLabelObjectEXT(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public boolean isNPOTTextureAvailable()
  {
    return downstreamGLES3.isNPOTTextureAvailable();
  }
  @Override
  public void glUniform4ui(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glUniform4ui("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniform4ui(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDrawElementsInstancedANGLE(int arg0,int arg1,int arg2,java.nio.Buffer arg3,int arg4)
  {
    printIndent();
    print(    "glDrawElementsInstancedANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.Buffer> "+arg3+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glDrawElementsInstancedANGLE(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetActiveAttrib(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4,java.nio.IntBuffer arg5,java.nio.ByteBuffer arg6)
  {
    printIndent();
    print(    "glGetActiveAttrib("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.IntBuffer> "+arg4+", "+"<java.nio.IntBuffer> "+arg5+", "+"<java.nio.ByteBuffer> "+arg6+")");
    downstreamGLES3.glGetActiveAttrib(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glCopyTextureLevelsAPPLE(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glCopyTextureLevelsAPPLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glCopyTextureLevelsAPPLE(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDrawBuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDrawBuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDrawBuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glTexStorage1D(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glTexStorage1D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glTexStorage1D(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glShaderBinary(int arg0,int[] arg1,int arg2,int arg3,java.nio.Buffer arg4,int arg5)
  {
    printIndent();
    print(    "glShaderBinary("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.Buffer> "+arg4+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glShaderBinary(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glCompressedTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,java.nio.Buffer arg7)
  {
    printIndent();
    print(    "glCompressedTexImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<java.nio.Buffer> "+arg7+")");
    downstreamGLES3.glCompressedTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glPushDebugGroup(int arg0,int arg1,int arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glPushDebugGroup("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glPushDebugGroup(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public int getMaxRenderbufferSamples()
  {
    return downstreamGLES3.getMaxRenderbufferSamples();
  }
  @Override
  public void glExtGetBuffersQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glExtGetBuffersQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glExtGetBuffersQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glExtGetBuffersQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glExtGetBuffersQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glExtGetBuffersQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public long glGetBufferSize(int arg0)
  {
    printIndent();
    print(    "glGetBufferSize("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    long _res = downstreamGLES3.glGetBufferSize(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glCompressedTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,long arg10)
  {
    printIndent();
    print(    "glCompressedTexSubImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+", "+"<long> "+arg10+")");
    downstreamGLES3.glCompressedTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public void glGetProgramiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetProgramiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetProgramiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform2f(int arg0,float arg1,float arg2)
  {
    printIndent();
    print(    "glUniform2f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+", "+"<float> "+arg2+")");
    downstreamGLES3.glUniform2f(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glSelectPerfMonitorCountersAMD(int arg0,boolean arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print(    "glSelectPerfMonitorCountersAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<boolean> "+arg1+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glSelectPerfMonitorCountersAMD(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glDeleteVertexArrays(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteVertexArrays("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteVertexArrays(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform2i(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glUniform2i("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glUniform2i(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDeleteTransformFeedbacks(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteTransformFeedbacks("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteTransformFeedbacks(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean glIsFenceNV(int arg0)
  {
    printIndent();
    print(    "glIsFenceNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsFenceNV(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glCompressedTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
    printIndent();
    print(    "glCompressedTexSubImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+", "+"<java.nio.Buffer> "+arg10+")");
    downstreamGLES3.glCompressedTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public void glPixelStorei(int arg0,int arg1)
  {
    printIndent();
    print(    "glPixelStorei("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glPixelStorei(arg0,arg1);
    println("");
  }
  @Override
  public int getSwapInterval()
  {
    return downstreamGLES3.getSwapInterval();
  }
  @Override
  public void glDeleteSamplers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteSamplers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteSamplers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetVertexAttribIiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetVertexAttribIiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetVertexAttribIiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glCopyTexSubImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8)
  {
    printIndent();
    print(    "glCopyTexSubImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+")");
    downstreamGLES3.glCopyTexSubImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public void glGetObjectLabel(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.ByteBuffer arg4)
  {
    printIndent();
    print(    "glGetObjectLabel("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.ByteBuffer> "+arg4+")");
    downstreamGLES3.glGetObjectLabel(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glEndTilingQCOM(int arg0)
  {
    printIndent();
    print(    "glEndTilingQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glEndTilingQCOM(arg0);
    println("");
  }
  @Override
  public void glGetVertexAttribiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetVertexAttribiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetVertexAttribiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetQueryObjecti64vEXT(int arg0,int arg1,long[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetQueryObjecti64vEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[J>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetQueryObjecti64vEXT(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glCompressedTexImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,long arg7)
  {
    printIndent();
    print(    "glCompressedTexImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<long> "+arg7+")");
    downstreamGLES3.glCompressedTexImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glDeleteProgram(int arg0)
  {
    printIndent();
    print(    "glDeleteProgram("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glDeleteProgram(arg0);
    println("");
  }
  @Override
  public int glCreateProgram()
  {
    printIndent();
    print(    "glCreateProgram("+")");
    int _res = downstreamGLES3.glCreateProgram();
    println(" = "+_res);
    return _res;
  }
  @Override
  public boolean isVBOArrayBound()
  {
    return downstreamGLES3.isVBOArrayBound();
  }
  @Override
  public void glGetShaderSource(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetShaderSource("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetShaderSource(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetnUniformiv(int arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetnUniformiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetnUniformiv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glCopyBufferSubData(int arg0,int arg1,long arg2,long arg3,long arg4)
  {
    printIndent();
    print(    "glCopyBufferSubData("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<long> "+arg2+", "+"<long> "+arg3+", "+"<long> "+arg4+")");
    downstreamGLES3.glCopyBufferSubData(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetTranslatedShaderSourceANGLE(int arg0,int arg1,int[] arg2,int arg3,byte[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetTranslatedShaderSourceANGLE("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetTranslatedShaderSourceANGLE(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glBindVertexArrayOES(int arg0)
  {
    printIndent();
    print(    "glBindVertexArrayOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    downstreamGLES3.glBindVertexArrayOES(arg0);
    println("");
  }
  @Override
  public void glInsertEventMarkerEXT(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print(    "glInsertEventMarkerEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glInsertEventMarkerEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glHint(int arg0,int arg1)
  {
    printIndent();
    print(    "glHint("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glHint(arg0,arg1);
    println("");
  }
  @Override
  public void glTexParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glTexParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glTexParameterfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTexStorage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5)
  {
    printIndent();
    print(    "glTexStorage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glTexStorage3D(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glExtGetShadersQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glExtGetShadersQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glExtGetShadersQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glExtGetShadersQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glExtGetShadersQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glExtGetShadersQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDeleteBuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteBuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteBuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glGetPerfMonitorGroupStringAMD(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetPerfMonitorGroupStringAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetPerfMonitorGroupStringAMD(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetPerfMonitorCounterStringAMD(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.ByteBuffer arg4)
  {
    printIndent();
    print(    "glGetPerfMonitorCounterStringAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.ByteBuffer> "+arg4+")");
    downstreamGLES3.glGetPerfMonitorCounterStringAMD(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGenPerfMonitorsAMD(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenPerfMonitorsAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenPerfMonitorsAMD(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glObjectPtrLabel(java.nio.Buffer arg0,int arg1,byte[] arg2,int arg3)
  {
    printIndent();
    print(    "glObjectPtrLabel("+"<java.nio.Buffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glObjectPtrLabel(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glExtGetFramebuffersQCOM(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glExtGetFramebuffersQCOM("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glExtGetFramebuffersQCOM(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glExtGetFramebuffersQCOM(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glExtGetFramebuffersQCOM("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glExtGetFramebuffersQCOM(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetFenceivNV(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetFenceivNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetFenceivNV(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniformMatrix3x4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix3x4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix3x4fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public int glGetBoundBuffer(int arg0)
  {
    printIndent();
    print(    "glGetBoundBuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    int _res = downstreamGLES3.glGetBoundBuffer(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetProgramInfoLog(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glGetProgramInfoLog("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glGetProgramInfoLog(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glShaderSource(int arg0,int arg1,java.lang.String[] arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glShaderSource("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[Ljava.lang.String;>"+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glShaderSource(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGenFencesNV(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenFencesNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenFencesNV(arg0,arg1,arg2);
    println("");
  }
  @Override
  public int getBoundFramebuffer(int arg0)
  {
    return downstreamGLES3.getBoundFramebuffer(arg0);
  }
  @Override
  public void glExtGetProgramBinarySourceQCOM(int arg0,int arg1,byte[] arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print(    "glExtGetProgramBinarySourceQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glExtGetProgramBinarySourceQCOM(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glRenderbufferStorage(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glRenderbufferStorage("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glRenderbufferStorage(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glTexParameterf(int arg0,int arg1,float arg2)
  {
    printIndent();
    print(    "glTexParameterf("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<float> "+arg2+")");
    downstreamGLES3.glTexParameterf(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniformMatrix3x4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix3x4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix3x4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glRenderbufferStorageMultisample(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glRenderbufferStorageMultisample("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glRenderbufferStorageMultisample(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetAttachedShaders(int arg0,int arg1,java.nio.IntBuffer arg2,java.nio.IntBuffer arg3)
  {
    printIndent();
    print(    "glGetAttachedShaders("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+", "+"<java.nio.IntBuffer> "+arg3+")");
    downstreamGLES3.glGetAttachedShaders(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetAttachedShaders(int arg0,int arg1,int[] arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetAttachedShaders("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetAttachedShaders(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glObjectLabel(int arg0,int arg1,int arg2,java.nio.ByteBuffer arg3)
  {
    printIndent();
    print(    "glObjectLabel("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.ByteBuffer> "+arg3+")");
    downstreamGLES3.glObjectLabel(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniform2uiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform2uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform2uiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetInteger64i_v(int arg0,int arg1,long[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetInteger64i_v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[J>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetInteger64i_v(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glEGLImageTargetRenderbufferStorageOES(int arg0,long arg1)
  {
    printIndent();
    print(    "glEGLImageTargetRenderbufferStorageOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<long> "+arg1+")");
    downstreamGLES3.glEGLImageTargetRenderbufferStorageOES(arg0,arg1);
    println("");
  }
  @Override
  public int getDefaultReadFramebuffer()
  {
    return downstreamGLES3.getDefaultReadFramebuffer();
  }
  @Override
  public void glGetBooleanv(int arg0,byte[] arg1,int arg2)
  {
    printIndent();
    print(    "glGetBooleanv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGetBooleanv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTexParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glTexParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glTexParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glBindSampler(int arg0,int arg1)
  {
    printIndent();
    print(    "glBindSampler("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBindSampler(arg0,arg1);
    println("");
  }
  @Override
  public void glUniform3i(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glUniform3i("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform3i(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGenBuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenBuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenBuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glGetPerfMonitorGroupsAMD(int[] arg0,int arg1,int arg2,int[] arg3,int arg4)
  {
    printIndent();
    print(    "glGetPerfMonitorGroupsAMD("+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glGetPerfMonitorGroupsAMD(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetPerfMonitorGroupsAMD(java.nio.IntBuffer arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetPerfMonitorGroupsAMD("+"<java.nio.IntBuffer> "+arg0+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetPerfMonitorGroupsAMD(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDeletePerfMonitorsAMD(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeletePerfMonitorsAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeletePerfMonitorsAMD(arg0,arg1);
    println("");
  }
  @Override
  public void glUniformMatrix2x3fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix2x3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix2x3fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniformMatrix2x4fv(int arg0,int arg1,boolean arg2,float[] arg3,int arg4)
  {
    printIndent();
    print(    "glUniformMatrix2x4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glUniformMatrix2x4fv(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glDeleteTextures(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteTextures("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteTextures(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGenFramebuffers(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenFramebuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenFramebuffers(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGenRenderbuffers(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenRenderbuffers("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenRenderbuffers(arg0,arg1);
    println("");
  }
  @Override
  public void glVertexAttribI4iv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glVertexAttribI4iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glVertexAttribI4iv(arg0,arg1);
    println("");
  }
  @Override
  public boolean glIsSync(long arg0)
  {
    printIndent();
    print(    "glIsSync("+"<long> "+arg0+")");
    boolean _res = downstreamGLES3.glIsSync(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glClearBufferuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glClearBufferuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glClearBufferuiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glDiscardFramebufferEXT(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glDiscardFramebufferEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glDiscardFramebufferEXT(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glTexStorage2D(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glTexStorage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glTexStorage2D(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetIntegerv(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGetIntegerv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGetIntegerv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetObjectLabelEXT(int arg0,int arg1,int arg2,int[] arg3,int arg4,byte[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetObjectLabelEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[B>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetObjectLabelEXT(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glClearDepth(double arg0)
  {
    printIndent();
    print(    "glClearDepth("+"<double> "+arg0+")");
    downstreamGLES3.glClearDepth(arg0);
    println("");
  }
  @Override
  public void glGenTransformFeedbacks(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenTransformFeedbacks("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenTransformFeedbacks(arg0,arg1);
    println("");
  }
  @Override
  public boolean isTextureFormatBGRA8888Available()
  {
    return downstreamGLES3.isTextureFormatBGRA8888Available();
  }
  @Override
  public boolean hasFullFBOSupport()
  {
    return downstreamGLES3.hasFullFBOSupport();
  }
  @Override
  public void glQueryCounterEXT(int arg0,int arg1)
  {
    printIndent();
    print(    "glQueryCounterEXT("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glQueryCounterEXT(arg0,arg1);
    println("");
  }
  @Override
  public void glPolygonOffset(float arg0,float arg1)
  {
    printIndent();
    print(    "glPolygonOffset("+"<float> "+arg0+", "+"<float> "+arg1+")");
    downstreamGLES3.glPolygonOffset(arg0,arg1);
    println("");
  }
  @Override
  public void glTexParameteri(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glTexParameteri("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glTexParameteri(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetTexParameteriv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetTexParameteriv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetTexParameteriv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glExtGetTexSubImageQCOM(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,int arg8,int arg9,java.nio.Buffer arg10)
  {
    printIndent();
    print(    "glExtGetTexSubImageQCOM("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg8).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg9).toUpperCase()+", "+"<java.nio.Buffer> "+arg10+")");
    downstreamGLES3.glExtGetTexSubImageQCOM(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8,arg9,arg10);
    println("");
  }
  @Override
  public void glBindRenderbuffer(int arg0,int arg1)
  {
    printIndent();
    print(    "glBindRenderbuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glBindRenderbuffer(arg0,arg1);
    println("");
  }
  @Override
  public void glUniform3f(int arg0,float arg1,float arg2,float arg3)
  {
    printIndent();
    print(    "glUniform3f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+", "+"<float> "+arg2+", "+"<float> "+arg3+")");
    downstreamGLES3.glUniform3f(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public boolean glIsShader(int arg0)
  {
    printIndent();
    print(    "glIsShader("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsShader(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glUniform3iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform3iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform3iv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniformMatrix2x3fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix2x3fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix2x3fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glUniformMatrix2x4fv(int arg0,int arg1,boolean arg2,java.nio.FloatBuffer arg3)
  {
    printIndent();
    print(    "glUniformMatrix2x4fv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<boolean> "+arg2+", "+"<java.nio.FloatBuffer> "+arg3+")");
    downstreamGLES3.glUniformMatrix2x4fv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetPerfMonitorCountersAMD(int arg0,java.nio.IntBuffer arg1,java.nio.IntBuffer arg2,int arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glGetPerfMonitorCountersAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+", "+"<java.nio.IntBuffer> "+arg2+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glGetPerfMonitorCountersAMD(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform2iv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform2iv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform2iv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glInvalidateFramebuffer(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glInvalidateFramebuffer("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glInvalidateFramebuffer(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDrawRangeElements(int arg0,int arg1,int arg2,int arg3,int arg4,long arg5)
  {
    printIndent();
    print(    "glDrawRangeElements("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<long> "+arg5+")");
    downstreamGLES3.glDrawRangeElements(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glCoverageMaskNV(boolean arg0)
  {
    printIndent();
    print(    "glCoverageMaskNV("+"<boolean> "+arg0+")");
    downstreamGLES3.glCoverageMaskNV(arg0);
    println("");
  }
  @Override
  public void glUniform1i(int arg0,int arg1)
  {
    printIndent();
    print(    "glUniform1i("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+")");
    downstreamGLES3.glUniform1i(arg0,arg1);
    println("");
  }
  @Override
  public void glStencilOp(int arg0,int arg1,int arg2)
  {
    printIndent();
    print(    "glStencilOp("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glStencilOp(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glCompressedTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print(    "glCompressedTexSubImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<java.nio.Buffer> "+arg8+")");
    downstreamGLES3.glCompressedTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public void glGenTextures(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glGenTextures("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glGenTextures(arg0,arg1);
    println("");
  }
  @Override
  public void glVertexAttribI4uiv(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glVertexAttribI4uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glVertexAttribI4uiv(arg0,arg1);
    println("");
  }
  @Override
  public void glCompressedTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,java.nio.Buffer arg8)
  {
    printIndent();
    print(    "glCompressedTexImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<java.nio.Buffer> "+arg8+")");
    downstreamGLES3.glCompressedTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public void glGetUniformiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetUniformiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetUniformiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetTexParameterfv(int arg0,int arg1,java.nio.FloatBuffer arg2)
  {
    printIndent();
    print(    "glGetTexParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.FloatBuffer> "+arg2+")");
    downstreamGLES3.glGetTexParameterfv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGenVertexArrays(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenVertexArrays("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenVertexArrays(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDeleteFencesNV(int arg0,java.nio.IntBuffer arg1)
  {
    printIndent();
    print(    "glDeleteFencesNV("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg1+")");
    downstreamGLES3.glDeleteFencesNV(arg0,arg1);
    println("");
  }
  @Override
  public void glUniform3uiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform3uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform3uiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glStencilOpSeparate(int arg0,int arg1,int arg2,int arg3)
  {
    printIndent();
    print(    "glStencilOpSeparate("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glStencilOpSeparate(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glGetVertexAttribIuiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetVertexAttribIuiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetVertexAttribIuiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glResumeTransformFeedback()
  {
    printIndent();
    print(    "glResumeTransformFeedback("+")");
    downstreamGLES3.glResumeTransformFeedback();
    println("");
  }
  @Override
  public void glUniform4uiv(int arg0,int arg1,int[] arg2,int arg3)
  {
    printIndent();
    print(    "glUniform4uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glUniform4uiv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public boolean glIsVBOArrayBound()
  {
    printIndent();
    print(    "glIsVBOArrayBound("+")");
    boolean _res = downstreamGLES3.glIsVBOArrayBound();
    println(" = "+_res);
    return _res;
  }
  @Override
  public void glGetTexParameterfv(int arg0,int arg1,float[] arg2,int arg3)
  {
    printIndent();
    print(    "glGetTexParameterfv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<[F>"+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+")");
    downstreamGLES3.glGetTexParameterfv(arg0,arg1,arg2,arg3);
    println("");
  }
  @Override
  public void glCompressedTexImage3D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print(    "glCompressedTexImage3D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<long> "+arg8+")");
    downstreamGLES3.glCompressedTexImage3D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public void glGenVertexArraysOES(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glGenVertexArraysOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glGenVertexArraysOES(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glGetIntegeri_v(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glGetIntegeri_v("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glGetIntegeri_v(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glUniform1uiv(int arg0,int arg1,java.nio.IntBuffer arg2)
  {
    printIndent();
    print(    "glUniform1uiv("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg2+")");
    downstreamGLES3.glUniform1uiv(arg0,arg1,arg2);
    println("");
  }
  @Override
  public void glDrawRangeElements(int arg0,int arg1,int arg2,int arg3,int arg4,java.nio.Buffer arg5)
  {
    printIndent();
    print(    "glDrawRangeElements("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<java.nio.Buffer> "+arg5+")");
    downstreamGLES3.glDrawRangeElements(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glGetInternalformativ(int arg0,int arg1,int arg2,int arg3,int[] arg4,int arg5)
  {
    printIndent();
    print(    "glGetInternalformativ("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+")");
    downstreamGLES3.glGetInternalformativ(arg0,arg1,arg2,arg3,arg4,arg5);
    println("");
  }
  @Override
  public void glVertexAttribI4i(int arg0,int arg1,int arg2,int arg3,int arg4)
  {
    printIndent();
    print(    "glVertexAttribI4i("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+")");
    downstreamGLES3.glVertexAttribI4i(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glGetPerfMonitorCounterDataAMD(int arg0,int arg1,int arg2,int[] arg3,int arg4,int[] arg5,int arg6)
  {
    printIndent();
    print(    "glGetPerfMonitorCounterDataAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+")");
    downstreamGLES3.glGetPerfMonitorCounterDataAMD(arg0,arg1,arg2,arg3,arg4,arg5,arg6);
    println("");
  }
  @Override
  public void glGetPerfMonitorCounterDataAMD(int arg0,int arg1,int arg2,java.nio.IntBuffer arg3,java.nio.IntBuffer arg4)
  {
    printIndent();
    print(    "glGetPerfMonitorCounterDataAMD("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<java.nio.IntBuffer> "+arg3+", "+"<java.nio.IntBuffer> "+arg4+")");
    downstreamGLES3.glGetPerfMonitorCounterDataAMD(arg0,arg1,arg2,arg3,arg4);
    println("");
  }
  @Override
  public void glUniform1f(int arg0,float arg1)
  {
    printIndent();
    print(    "glUniform1f("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<float> "+arg1+")");
    downstreamGLES3.glUniform1f(arg0,arg1);
    println("");
  }
  @Override
  public void glCompressedTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7,long arg8)
  {
    printIndent();
    print(    "glCompressedTexSubImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+", "+"<long> "+arg8+")");
    downstreamGLES3.glCompressedTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8);
    println("");
  }
  @Override
  public void glUniform(javax.media.opengl.GLUniformData arg0)
  {
    printIndent();
    print(    "glUniform("+"<javax.media.opengl.GLUniformData> "+arg0+")");
    downstreamGLES3.glUniform(arg0);
    println("");
  }
  @Override
  public void glLineWidth(float arg0)
  {
    printIndent();
    print(    "glLineWidth("+"<float> "+arg0+")");
    downstreamGLES3.glLineWidth(arg0);
    println("");
  }
  @Override
  public void glCopyTexSubImage2D(int arg0,int arg1,int arg2,int arg3,int arg4,int arg5,int arg6,int arg7)
  {
    printIndent();
    print(    "glCopyTexSubImage2D("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg1).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg3).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg4).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg5).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg6).toUpperCase()+", "+"<int> 0x"+Integer.toHexString(arg7).toUpperCase()+")");
    downstreamGLES3.glCopyTexSubImage2D(arg0,arg1,arg2,arg3,arg4,arg5,arg6,arg7);
    println("");
  }
  @Override
  public void glDeleteVertexArraysOES(int arg0,int[] arg1,int arg2)
  {
    printIndent();
    print(    "glDeleteVertexArraysOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+", "+"<[I>"+", "+"<int> 0x"+Integer.toHexString(arg2).toUpperCase()+")");
    downstreamGLES3.glDeleteVertexArraysOES(arg0,arg1,arg2);
    println("");
  }
  @Override
  public boolean glIsVertexArrayOES(int arg0)
  {
    printIndent();
    print(    "glIsVertexArrayOES("+"<int> 0x"+Integer.toHexString(arg0).toUpperCase()+")");
    boolean _res = downstreamGLES3.glIsVertexArrayOES(arg0);
    println(" = "+_res);
    return _res;
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("TraceGLES3 [this 0x"+Integer.toHexString(hashCode())+" implementing javax.media.opengl.GLES3,\n\t");
    sb.append(" downstream: "+downstreamGLES3.toString()+"\n\t]");
    return sb.toString();
  }
private PrintStream stream;
private int indent = 0;
protected String dumpArray(Object obj)
{
  if (obj == null) return "[null]";
  StringBuilder sb = new StringBuilder("[");
  int len  = java.lang.reflect.Array.getLength(obj);
  int count = Math.min(len,16);
  for ( int i =0; i < count; i++ ) {
    sb.append(java.lang.reflect.Array.get(obj,i));
    if (i < count-1)
      sb.append(',');
  }
  if ( len > 16 )
    sb.append("...").append(len);
  sb.append(']');
  return sb.toString();
}
protected void print(String str)
{
  stream.print(str);
}
protected void println(String str)
{
  stream.println(str);
}
protected void printIndent()
{
  for( int i =0; i < indent; i++) {stream.print(' ');}
}

  private GLES3 downstreamGLES3;
} // end class TraceGLES3
