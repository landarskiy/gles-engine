/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Tue Mar 11 04:18:13 CET 2014 ----! */

package javax.media.opengl;

import java.util.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import jogamp.opengl.*;
import java.io.PrintStream;
import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

 /**
  * <p> 
  * Interface containing the common subset of core GL4 (OpenGL 4.0+) and GLES3 (OpenGL ES 3.0).<br/>
  * This interface reflects only the programmable shader functionality of desktop and embedded OpenGL<br/>
  * This interface is GLES3 complete w/o vendor extensions.<br/>
  * </p>
  */
public interface GL4ES3 extends GL3ES3{

  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX = 0x8D69;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code> */
  public static final int GL_TRANSFORM_FEEDBACK = 0x8E22;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code> */
  public static final int GL_TRANSFORM_FEEDBACK_BINDING = 0x8E25;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_1</code>, <code>GL_ARB_get_program_binary</code> */
  public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 0x8257;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_R11_EAC = 0x9270;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_SIGNED_R11_EAC = 0x9271;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_RG11_EAC = 0x9272;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_SIGNED_RG11_EAC = 0x9273;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_RGB8_ETC2 = 0x9274;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_SRGB8_ETC2 = 0x9275;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9276;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9277;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_RGBA8_ETC2_EAC = 0x9278;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 0x9279;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_ES3_compatibility</code> */
  public static final int GL_MAX_ELEMENT_INDEX = 0x8D6B;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_ARB_internalformat_query</code>, <code>GL_VERSION_4_2</code> */
  public static final int GL_NUM_SAMPLE_COUNTS = 0x9380;
  /** Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_3</code>, <code>GL_ARB_texture_view</code> */
  public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 0x82DF;

  /** Entry point to C language function: <code> void {@native glBindTransformFeedback}(GLenum target, GLuint id); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>   */
  public void glBindTransformFeedback(int target, int id);

  /** Entry point to C language function: <code> void {@native glDeleteTransformFeedbacks}(GLsizei n, const GLuint *  ids); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>
      @param ids a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glDeleteTransformFeedbacks(int n, IntBuffer ids);

  /** Entry point to C language function: <code> void {@native glDeleteTransformFeedbacks}(GLsizei n, const GLuint *  ids); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>   */
  public void glDeleteTransformFeedbacks(int n, int[] ids, int ids_offset);

  /** Entry point to C language function: <code> void {@native glGenTransformFeedbacks}(GLsizei n, GLuint *  ids); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>
      @param ids a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glGenTransformFeedbacks(int n, IntBuffer ids);

  /** Entry point to C language function: <code> void {@native glGenTransformFeedbacks}(GLsizei n, GLuint *  ids); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>   */
  public void glGenTransformFeedbacks(int n, int[] ids, int ids_offset);

  /** Entry point to C language function: <code> void {@native glInvalidateFramebuffer}(GLenum target, GLsizei numAttachments, const GLenum *  attachments); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_ARB_invalidate_subdata</code>, <code>GL_VERSION_4_3</code>
      @param attachments a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glInvalidateFramebuffer(int target, int numAttachments, IntBuffer attachments);

  /** Entry point to C language function: <code> void {@native glInvalidateFramebuffer}(GLenum target, GLsizei numAttachments, const GLenum *  attachments); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_ARB_invalidate_subdata</code>, <code>GL_VERSION_4_3</code>   */
  public void glInvalidateFramebuffer(int target, int numAttachments, int[] attachments, int attachments_offset);

  /** Entry point to C language function: <code> void {@native glInvalidateSubFramebuffer}(GLenum target, GLsizei numAttachments, const GLenum *  attachments, GLint x, GLint y, GLsizei width, GLsizei height); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_ARB_invalidate_subdata</code>, <code>GL_VERSION_4_3</code>
      @param attachments a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glInvalidateSubFramebuffer(int target, int numAttachments, IntBuffer attachments, int x, int y, int width, int height);

  /** Entry point to C language function: <code> void {@native glInvalidateSubFramebuffer}(GLenum target, GLsizei numAttachments, const GLenum *  attachments, GLint x, GLint y, GLsizei width, GLsizei height); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_ARB_invalidate_subdata</code>, <code>GL_VERSION_4_3</code>   */
  public void glInvalidateSubFramebuffer(int target, int numAttachments, int[] attachments, int attachments_offset, int x, int y, int width, int height);

  /** Entry point to C language function: <code> GLboolean {@native glIsTransformFeedback}(GLuint id); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>   */
  public boolean glIsTransformFeedback(int id);

  /** Entry point to C language function: <code> void {@native glPauseTransformFeedback}(void); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>   */
  public void glPauseTransformFeedback();

  /** Entry point to C language function: <code> void {@native glProgramParameteri}(GLuint program, GLenum pname, GLint value); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_1</code>, <code>GL_ARB_get_program_binary</code>; <code>GL_EXT_separate_shader_objects</code>, <code>GL_EXT_geometry_shader4</code>   */
  public void glProgramParameteri(int program, int pname, int value);

  /** Entry point to C language function: <code> void {@native glResumeTransformFeedback}(void); </code> <br>Part of <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_4_0</code>, <code>GL_ARB_transform_feedback2</code>   */
  public void glResumeTransformFeedback();


} // end of class GL4ES3
