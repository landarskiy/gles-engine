/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Tue Mar 11 04:16:35 CET 2014 ----! */

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
  * <p> 
  * Interface containing the common subset of GL2 and GLES1.<br/>
  * This interface reflects only the fixed functionality of OpenGL<br/>
  * </p>
  */
public interface GL2ES1 extends GL, GLMatrixFunc, GLPointerFunc, GLLightingFunc{

  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CLIP_PLANE0 = 0x3000;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CLIP_PLANE1 = 0x3001;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CLIP_PLANE2 = 0x3002;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CLIP_PLANE3 = 0x3003;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CLIP_PLANE4 = 0x3004;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CLIP_PLANE5 = 0x3005;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG = 0x0B60;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_ALPHA_TEST = 0x0BC0;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_POINT_SMOOTH = 0x0B10;
  /** Part of <code>GL_VERSION_1_2</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_RESCALE_NORMAL = 0x803A;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_STACK_OVERFLOW = 0x0503;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_STACK_UNDERFLOW = 0x0504;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_EXP = 0x0800;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_EXP2 = 0x0801;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG_DENSITY = 0x0B62;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG_START = 0x0B63;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG_END = 0x0B64;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG_MODE = 0x0B65;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG_COLOR = 0x0B66;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CURRENT_COLOR = 0x0B00;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CURRENT_NORMAL = 0x0B02;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_CURRENT_TEXTURE_COORDS = 0x0B03;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_4</code> */
  public static final int GL_POINT_SIZE_MIN = 0x8126;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_4</code> */
  public static final int GL_POINT_SIZE_MAX = 0x8127;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_4</code> */
  public static final int GL_POINT_DISTANCE_ATTENUATION = 0x8129;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SHADE_MODEL = 0x0B54;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MODELVIEW_STACK_DEPTH = 0x0BA3;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_PROJECTION_STACK_DEPTH = 0x0BA4;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_STACK_DEPTH = 0x0BA5;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_ALPHA_TEST_FUNC = 0x0BC1;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_ALPHA_TEST_REF = 0x0BC2;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MAX_LIGHTS = 0x0D31;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MAX_CLIP_PLANES = 0x0D32;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MAX_MODELVIEW_STACK_DEPTH = 0x0D36;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MAX_PROJECTION_STACK_DEPTH = 0x0D38;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MAX_TEXTURE_STACK_DEPTH = 0x0D39;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_MAX_TEXTURE_UNITS = 0x84E2;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_VERTEX_ARRAY_SIZE = 0x807A;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_VERTEX_ARRAY_TYPE = 0x807B;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_VERTEX_ARRAY_STRIDE = 0x807C;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_NORMAL_ARRAY_TYPE = 0x807E;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_NORMAL_ARRAY_STRIDE = 0x807F;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_COLOR_ARRAY_SIZE = 0x8081;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_COLOR_ARRAY_TYPE = 0x8082;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_COLOR_ARRAY_STRIDE = 0x8083;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 0x8088;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 0x8089;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 0x808A;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_VERTEX_ARRAY_POINTER = 0x808E;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_NORMAL_ARRAY_POINTER = 0x808F;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_COLOR_ARRAY_POINTER = 0x8090;
  /** Part of <code>GL_VERSION_1_1</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 0x8092;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_PERSPECTIVE_CORRECTION_HINT = 0x0C50;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_POINT_SMOOTH_HINT = 0x0C51;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_FOG_HINT = 0x0C54;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_LIGHT_MODEL_AMBIENT = 0x0B53;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_LIGHT_MODEL_TWO_SIDE = 0x0B52;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_MODULATE = 0x2100;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_DECAL = 0x2101;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_ADD = 0x0104;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_ENV_MODE = 0x2200;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_ENV_COLOR = 0x2201;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_ENV = 0x2300;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_4</code> */
  public static final int GL_GENERATE_MIPMAP = 0x8191;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_CLIENT_ACTIVE_TEXTURE = 0x84E1;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_VERTEX_ARRAY_BUFFER_BINDING = 0x8896;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_NORMAL_ARRAY_BUFFER_BINDING = 0x8897;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_COLOR_ARRAY_BUFFER_BINDING = 0x8898;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING = 0x889A;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_SUBTRACT = 0x84E7;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_COMBINE = 0x8570;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_COMBINE_RGB = 0x8571;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_COMBINE_ALPHA = 0x8572;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_RGB_SCALE = 0x8573;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_ADD_SIGNED = 0x8574;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_INTERPOLATE = 0x8575;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_CONSTANT = 0x8576;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_PRIMARY_COLOR = 0x8577;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_PREVIOUS = 0x8578;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_OPERAND0_RGB = 0x8590;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_OPERAND1_RGB = 0x8591;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_OPERAND2_RGB = 0x8592;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_OPERAND0_ALPHA = 0x8598;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_OPERAND1_ALPHA = 0x8599;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_OPERAND2_ALPHA = 0x859A;
  /** Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_ALPHA_SCALE = 0x0D1C;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SRC0_RGB = 0x8580;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SRC1_RGB = 0x8581;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SRC2_RGB = 0x8582;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SRC0_ALPHA = 0x8588;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SRC1_ALPHA = 0x8589;
  /** Part of <code>GL_VERSION_1_5</code>, <code>GL_VERSION_ES_1_0</code> */
  public static final int GL_SRC2_ALPHA = 0x858A;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_DOT3_RGB = 0x86AE;
  /** Part of <code>GL_VERSION_ES_1_0</code>, <code>GL_VERSION_1_3</code> */
  public static final int GL_DOT3_RGBA = 0x86AF;
  /** Part of <code>GL_VERSION_2_0</code>; <code>GL_ARB_point_sprite</code>; <code>GL_OES_point_sprite</code> */
  public static final int GL_POINT_SPRITE = 0x8861;
  /** Part of <code>GL_VERSION_2_0</code>; <code>GL_ARB_point_sprite</code>; <code>GL_OES_point_sprite</code> */
  public static final int GL_COORD_REPLACE = 0x8862;
  /** Part of <code>GL_ARB_vertex_blend</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MAX_VERTEX_UNITS = 0x86A4;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MAX_PALETTE_MATRICES = 0x8842;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MATRIX_PALETTE = 0x8840;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MATRIX_INDEX_ARRAY = 0x8844;
  /** Part of <code>GL_ARB_vertex_blend</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_WEIGHT_ARRAY = 0x86AD;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_CURRENT_PALETTE_MATRIX = 0x8843;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MATRIX_INDEX_ARRAY_SIZE = 0x8846;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MATRIX_INDEX_ARRAY_TYPE = 0x8847;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MATRIX_INDEX_ARRAY_STRIDE = 0x8848;
  /** Part of <code>GL_ARB_matrix_palette</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_MATRIX_INDEX_ARRAY_POINTER = 0x8849;
  /** Part of <code>GL_ARB_vertex_blend</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_WEIGHT_ARRAY_SIZE = 0x86AB;
  /** Part of <code>GL_ARB_vertex_blend</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_WEIGHT_ARRAY_TYPE = 0x86A9;
  /** Part of <code>GL_ARB_vertex_blend</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_WEIGHT_ARRAY_STRIDE = 0x86AA;
  /** Part of <code>GL_ARB_vertex_blend</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_WEIGHT_ARRAY_POINTER = 0x86AC;
  /** Part of <code>GL_VERSION_1_5</code>; <code>GL_OES_matrix_palette</code> */
  public static final int GL_WEIGHT_ARRAY_BUFFER_BINDING = 0x889E;
  /** Part of <code>GL_VERSION_1_3</code>; <code>GL_OES_texture_cube_map</code> */
  public static final int GL_NORMAL_MAP = 0x8511;
  /** Part of <code>GL_VERSION_1_3</code>; <code>GL_OES_texture_cube_map</code> */
  public static final int GL_REFLECTION_MAP = 0x8512;
  /** Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code> */
  public static final int GL_TEXTURE_GEN_MODE = 0x2500;
  /** Part of <code>GL_OES_texture_cube_map</code> */
  public static final int GL_TEXTURE_GEN_STR = 0x8D60;
  /** Part of <code>GL_EXT_robustness</code> */
  public static final int GL_CONTEXT_ROBUST_ACCESS = 0x90F3;
  /** Part of <code>GL_ARB_framebuffer_object</code>, <code>GL_ES_VERSION_3_0</code>, <code>GL_VERSION_3_0</code>; <code>GL_EXT_sRGB</code> */
  public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 0x8210;

  /** Entry point to C language function: <code> void {@native glAlphaFunc}(GLenum func, GLclampf ref); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glAlphaFunc(int func, float ref);

  /** Entry point to C language function: <code> void {@native glClientActiveTexture}(GLenum texture); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_3</code>   */
  public void glClientActiveTexture(int texture);

  /** Entry point to C language function: <code> void {@native glColor4ub}(GLubyte red, GLubyte green, GLubyte blue, GLubyte alpha); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>   */
  public void glColor4ub(byte red, byte green, byte blue, byte alpha);

  /** Entry point to C language function: <code> void {@native glFogf}(GLenum pname, GLfloat param); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glFogf(int pname, float param);

  /** Entry point to C language function: <code> void {@native glFogfv}(GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glFogfv(int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glFogfv}(GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glFogfv(int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glGetLightfv}(GLenum light, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glGetLightfv(int light, int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glGetLightfv}(GLenum light, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glGetLightfv(int light, int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glGetMaterialfv}(GLenum face, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glGetMaterialfv(int face, int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glGetMaterialfv}(GLenum face, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glGetMaterialfv(int face, int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glGetTexEnvfv}(GLenum tenv, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glGetTexEnvfv(int tenv, int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glGetTexEnvfv}(GLenum tenv, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glGetTexEnvfv(int tenv, int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glGetTexEnviv}(GLenum tenv, GLenum pname, GLint *  params); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>
      @param params a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glGetTexEnviv(int tenv, int pname, IntBuffer params);

  /** Entry point to C language function: <code> void {@native glGetTexEnviv}(GLenum tenv, GLenum pname, GLint *  params); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>   */
  public void glGetTexEnviv(int tenv, int pname, int[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glGetTexGenfv}(GLenum coord, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glGetTexGenfv(int coord, int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glGetTexGenfv}(GLenum coord, GLenum pname, GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>   */
  public void glGetTexGenfv(int coord, int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glGetTexGeniv}(GLenum coord, GLenum pname, GLint *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>
      @param params a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glGetTexGeniv(int coord, int pname, IntBuffer params);

  /** Entry point to C language function: <code> void {@native glGetTexGeniv}(GLenum coord, GLenum pname, GLint *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>   */
  public void glGetTexGeniv(int coord, int pname, int[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glLightModelf}(GLenum pname, GLfloat param); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glLightModelf(int pname, float param);

  /** Entry point to C language function: <code> void {@native glLightModelfv}(GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glLightModelfv(int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glLightModelfv}(GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glLightModelfv(int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glLightf}(GLenum light, GLenum pname, GLfloat param); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glLightf(int light, int pname, float param);

  /** Entry point to C language function: <code> void {@native glLogicOp}(GLenum opcode); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>   */
  public void glLogicOp(int opcode);

  /** Entry point to C language function: <code> void {@native glMultiTexCoord4f}(GLenum target, GLfloat s, GLfloat t, GLfloat r, GLfloat q); </code> <br>Part of <code>GL_VERSION_1_3</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glMultiTexCoord4f(int target, float s, float t, float r, float q);

  /** Entry point to C language function: <code> void {@native glNormal3f}(GLfloat nx, GLfloat ny, GLfloat nz); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glNormal3f(float nx, float ny, float nz);

  /** Entry point to C language function: <code> void {@native glPointParameterf}(GLenum pname, GLfloat param); </code> <br>Part of <code>GL_VERSION_1_4</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glPointParameterf(int pname, float param);

  /** Entry point to C language function: <code> void {@native glPointParameterfv}(GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_4</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glPointParameterfv(int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glPointParameterfv}(GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_4</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glPointParameterfv(int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glPointSize}(GLfloat size); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glPointSize(float size);

  /** Entry point to C language function: <code> void {@native glTexEnvf}(GLenum target, GLenum pname, GLfloat param); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glTexEnvf(int target, int pname, float param);

  /** Entry point to C language function: <code> void {@native glTexEnvfv}(GLenum target, GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glTexEnvfv(int target, int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glTexEnvfv}(GLenum target, GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>, <code>GL_VERSION_ES_CM</code>   */
  public void glTexEnvfv(int target, int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glTexEnvi}(GLenum target, GLenum pname, GLint param); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>   */
  public void glTexEnvi(int target, int pname, int param);

  /** Entry point to C language function: <code> void {@native glTexEnviv}(GLenum target, GLenum pname, const GLint *  params); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>
      @param params a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glTexEnviv(int target, int pname, IntBuffer params);

  /** Entry point to C language function: <code> void {@native glTexEnviv}(GLenum target, GLenum pname, const GLint *  params); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_0</code>   */
  public void glTexEnviv(int target, int pname, int[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glTexGenf}(GLenum coord, GLenum pname, GLfloat param); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>   */
  public void glTexGenf(int coord, int pname, float param);

  /** Entry point to C language function: <code> void {@native glTexGenfv}(GLenum coord, GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>
      @param params a direct or array-backed {@link java.nio.FloatBuffer}   */
  public void glTexGenfv(int coord, int pname, FloatBuffer params);

  /** Entry point to C language function: <code> void {@native glTexGenfv}(GLenum coord, GLenum pname, const GLfloat *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>   */
  public void glTexGenfv(int coord, int pname, float[] params, int params_offset);

  /** Entry point to C language function: <code> void {@native glTexGeni}(GLenum coord, GLenum pname, GLint param); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>   */
  public void glTexGeni(int coord, int pname, int param);

  /** Entry point to C language function: <code> void {@native glTexGeniv}(GLenum coord, GLenum pname, const GLint *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>
      @param params a direct or array-backed {@link java.nio.IntBuffer}   */
  public void glTexGeniv(int coord, int pname, IntBuffer params);

  /** Entry point to C language function: <code> void {@native glTexGeniv}(GLenum coord, GLenum pname, const GLint *  params); </code> <br>Part of <code>GL_VERSION_1_0</code>; <code>GL_OES_texture_cube_map</code>   */
  public void glTexGeniv(int coord, int pname, int[] params, int params_offset);


  // --- Begin CustomJavaCode .cfg declarations
 public void glOrtho(double left, double right, double bottom, double top, double near_val, double far_val);
 public void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar);
    /** Entry point to C language function: <code> void {@native glDrawElements}(GLenum mode, GLsizei count, GLenum type, const GLvoid *  indices); </code> <br>Part of <code>GL_VERSION_ES_CL_CM</code>, <code>GL_VERSION_1_1</code>, <code>GL_ES_VERSION_2_0</code>
        @param indices a direct or array-backed {@link java.nio.Buffer}   */
    public void glDrawElements(int mode, int count, int type, Buffer indices);
  
  // ---- End CustomJavaCode .cfg declarations

} // end of class GL2ES1
