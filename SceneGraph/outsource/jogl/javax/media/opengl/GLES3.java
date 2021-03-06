/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Tue Mar 11 04:18:19 CET 2014 ----! */

package javax.media.opengl;

import java.util.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import jogamp.opengl.*;
import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

 /**
  * <p>This interface contains all OpenGL ES 3.0 methods,
  * as well as most of it's extensions defined at the time of this specification.</p>
  */
public interface GLES3 extends GLES2, GL4ES3{



  // --- Begin CustomJavaCode .cfg declarations
    /** Entry point to C language function: <code> void {@native glVertexAttribPointer}(GLuint indx, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const GLvoid *  ptr); </code> <br>Part of <code>GL_ES_VERSION_2_0</code>, <code>GL_VERSION_2_0</code>
        @param ptr a direct only {@link java.nio.Buffer}   */
    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr);
  
  
    /** Entry point to C language function: <code> void {@native glDrawElementsInstanced}(GLenum mode, GLsizei count, GLenum type, const GLvoid *  indices, GLsizei instancecount); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_3_1</code>; <code>GL_ARB_draw_instanced</code>
        @param indices a direct or array-backed {@link java.nio.Buffer}   */
    public void glDrawElementsInstanced(int mode, int count, int type, Buffer indices, int instancecount);
  
    /** Entry point to C language function: <code> void {@native glDrawRangeElements}(GLenum mode, GLuint start, GLuint end, GLsizei count, GLenum type, const GLvoid *  indices); </code> <br>Part of <code>GL_VERSION_1_2</code>, <code>GL_ES_VERSION_3_0</code>
        @param indices a direct or array-backed {@link java.nio.Buffer}   */
    public void glDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices);
  
    /** Entry point to C language function: <code> void {@native glVertexAttribIPointer}(GLuint index, GLint size, GLenum type, GLsizei stride, const GLvoid *  pointer); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_3_0</code>
        @param pointer a direct only {@link java.nio.Buffer}   */
    public void glVertexAttribIPointer(int index, int size, int type, int stride, Buffer pointer);
  
  // ---- End CustomJavaCode .cfg declarations

} // end of class GLES3
