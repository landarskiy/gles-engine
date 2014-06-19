/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Tue Mar 11 04:16:40 CET 2014 ----! */

package jogamp.opengl.es1;

import java.util.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import jogamp.opengl.*;
import javax.media.opengl.GLES1;
import javax.media.opengl.GLES2;
import javax.media.opengl.GL2;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.GLBuffers;
import com.jogamp.gluegen.runtime.ProcAddressTable;
import com.jogamp.common.util.SecurityUtil;

/**
 * This table is a cache of pointers to the dynamically-linkable C library.
 * @see ProcAddressTable
 */
public final class GLES1ProcAddressTable extends ProcAddressTable {

  /* pp */ long _addressof_glBufferData;
  /* pp */ long _addressof_glMapBuffer;
  /* pp */ long _addressof_glMapBufferRange;
  /* pp */ long _addressof_glUnmapBuffer;

  public GLES1ProcAddressTable(){ super(); }

  public GLES1ProcAddressTable(com.jogamp.gluegen.runtime.FunctionAddressResolver resolver){ super(resolver); }

  /* pp */ long _addressof_glActiveTexture;
  /* pp */ long _addressof_glAlphaFunc;
  /* pp */ long _addressof_glAlphaFuncx;
  /* pp */ long _addressof_glBindBuffer;
  /* pp */ long _addressof_glBindFramebuffer;
  /* pp */ long _addressof_glBindRenderbuffer;
  /* pp */ long _addressof_glBindTexture;
  /* pp */ long _addressof_glBindVertexArrayOES;
  /* pp */ long _addressof_glBlendEquation;
  /* pp */ long _addressof_glBlendEquationSeparate;
  /* pp */ long _addressof_glBlendFunc;
  /* pp */ long _addressof_glBlendFuncSeparate;
  /* pp */ long _addressof_glBufferSubData;
  /* pp */ long _addressof_glCheckFramebufferStatus;
  /* pp */ long _addressof_glClear;
  /* pp */ long _addressof_glClearColor;
  /* pp */ long _addressof_glClearColorx;
  /* pp */ long _addressof_glClearDepthf;
  /* pp */ long _addressof_glClearDepthx;
  /* pp */ long _addressof_glClearStencil;
  /* pp */ long _addressof_glClientActiveTexture;
  /* pp */ long _addressof_glClipPlanef;
  /* pp */ long _addressof_glClipPlanefIMG;
  /* pp */ long _addressof_glClipPlanex;
  /* pp */ long _addressof_glClipPlanexIMG;
  /* pp */ long _addressof_glColor4f;
  /* pp */ long _addressof_glColor4ub;
  /* pp */ long _addressof_glColor4x;
  /* pp */ long _addressof_glColorMask;
  /* pp */ long _addressof_glColorPointer;
  /* pp */ long _addressof_glCompressedTexImage2D;
  /* pp */ long _addressof_glCompressedTexSubImage2D;
  /* pp */ long _addressof_glCopyTexImage2D;
  /* pp */ long _addressof_glCopyTexSubImage2D;
  /* pp */ long _addressof_glCopyTextureLevelsAPPLE;
  /* pp */ long _addressof_glCullFace;
  /* pp */ long _addressof_glCurrentPaletteMatrix;
  /* pp */ long _addressof_glDeleteBuffers;
  /* pp */ long _addressof_glDeleteFencesNV;
  /* pp */ long _addressof_glDeleteFramebuffers;
  /* pp */ long _addressof_glDeleteRenderbuffers;
  /* pp */ long _addressof_glDeleteTextures;
  /* pp */ long _addressof_glDeleteVertexArraysOES;
  /* pp */ long _addressof_glDepthFunc;
  /* pp */ long _addressof_glDepthMask;
  /* pp */ long _addressof_glDepthRangef;
  /* pp */ long _addressof_glDepthRangex;
  /* pp */ long _addressof_glDisable;
  /* pp */ long _addressof_glDisableClientState;
  /* pp */ long _addressof_glDisableDriverControlQCOM;
  /* pp */ long _addressof_glDiscardFramebufferEXT;
  /* pp */ long _addressof_glDrawArrays;
  /* pp */ long _addressof_glDrawElements;
  /* pp */ long _addressof_glDrawTexfOES;
  /* pp */ long _addressof_glDrawTexfvOES;
  /* pp */ long _addressof_glDrawTexiOES;
  /* pp */ long _addressof_glDrawTexivOES;
  /* pp */ long _addressof_glDrawTexsOES;
  /* pp */ long _addressof_glDrawTexsvOES;
  /* pp */ long _addressof_glDrawTexxOES;
  /* pp */ long _addressof_glDrawTexxvOES;
  /* pp */ long _addressof_glEGLImageTargetRenderbufferStorageOES;
  /* pp */ long _addressof_glEGLImageTargetTexture2DOES;
  /* pp */ long _addressof_glEnable;
  /* pp */ long _addressof_glEnableClientState;
  /* pp */ long _addressof_glEnableDriverControlQCOM;
  /* pp */ long _addressof_glEndTilingQCOM;
  /* pp */ long _addressof_glExtGetBufferPointervQCOM;
  /* pp */ long _addressof_glExtGetBuffersQCOM;
  /* pp */ long _addressof_glExtGetFramebuffersQCOM;
  /* pp */ long _addressof_glExtGetProgramBinarySourceQCOM;
  /* pp */ long _addressof_glExtGetProgramsQCOM;
  /* pp */ long _addressof_glExtGetRenderbuffersQCOM;
  /* pp */ long _addressof_glExtGetShadersQCOM;
  /* pp */ long _addressof_glExtGetTexLevelParameterivQCOM;
  /* pp */ long _addressof_glExtGetTexSubImageQCOM;
  /* pp */ long _addressof_glExtGetTexturesQCOM;
  /* pp */ long _addressof_glExtIsProgramBinaryQCOM;
  /* pp */ long _addressof_glExtTexObjectStateOverrideiQCOM;
  /* pp */ long _addressof_glFinish;
  /* pp */ long _addressof_glFinishFenceNV;
  /* pp */ long _addressof_glFlush;
  /* pp */ long _addressof_glFlushMappedBufferRange;
  /* pp */ long _addressof_glFogf;
  /* pp */ long _addressof_glFogfv;
  /* pp */ long _addressof_glFogx;
  /* pp */ long _addressof_glFogxv;
  /* pp */ long _addressof_glFramebufferRenderbuffer;
  /* pp */ long _addressof_glFramebufferTexture2DMultisampleEXT;
  /* pp */ long _addressof_glFramebufferTexture2DMultisampleIMG;
  /* pp */ long _addressof_glFramebufferTexture2D;
  /* pp */ long _addressof_glFrontFace;
  /* pp */ long _addressof_glFrustumf;
  /* pp */ long _addressof_glFrustumx;
  /* pp */ long _addressof_glGenBuffers;
  /* pp */ long _addressof_glGenFencesNV;
  /* pp */ long _addressof_glGenFramebuffers;
  /* pp */ long _addressof_glGenRenderbuffers;
  /* pp */ long _addressof_glGenTextures;
  /* pp */ long _addressof_glGenVertexArraysOES;
  /* pp */ long _addressof_glGenerateMipmap;
  /* pp */ long _addressof_glGetBooleanv;
  /* pp */ long _addressof_glGetBufferParameteriv;
  /* pp */ long _addressof_glGetClipPlanef;
  /* pp */ long _addressof_glGetClipPlanex;
  /* pp */ long _addressof_glGetDriverControlStringQCOM;
  /* pp */ long _addressof_glGetDriverControlsQCOM;
  /* pp */ long _addressof_glGetError;
  /* pp */ long _addressof_glGetFenceivNV;
  /* pp */ long _addressof_glGetFixedv;
  /* pp */ long _addressof_glGetFloatv;
  /* pp */ long _addressof_glGetFramebufferAttachmentParameteriv;
  /* pp */ long _addressof_glGetGraphicsResetStatus;
  /* pp */ long _addressof_glGetIntegerv;
  /* pp */ long _addressof_glGetLightfv;
  /* pp */ long _addressof_glGetLightxv;
  /* pp */ long _addressof_glGetMaterialfv;
  /* pp */ long _addressof_glGetMaterialxv;
  /* pp */ long _addressof_glGetRenderbufferParameteriv;
  /* pp */ long _addressof_glGetString;
  /* pp */ long _addressof_glGetTexEnvfv;
  /* pp */ long _addressof_glGetTexEnviv;
  /* pp */ long _addressof_glGetTexEnvxv;
  /* pp */ long _addressof_glGetTexGenfv;
  /* pp */ long _addressof_glGetTexGeniv;
  /* pp */ long _addressof_glGetTexParameterfv;
  /* pp */ long _addressof_glGetTexParameteriv;
  /* pp */ long _addressof_glGetTexParameterxv;
  /* pp */ long _addressof_glGetnUniformfv;
  /* pp */ long _addressof_glGetnUniformiv;
  /* pp */ long _addressof_glHint;
  /* pp */ long _addressof_glIsBuffer;
  /* pp */ long _addressof_glIsEnabled;
  /* pp */ long _addressof_glIsFenceNV;
  /* pp */ long _addressof_glIsFramebuffer;
  /* pp */ long _addressof_glIsRenderbuffer;
  /* pp */ long _addressof_glIsTexture;
  /* pp */ long _addressof_glIsVertexArrayOES;
  /* pp */ long _addressof_glLightModelf;
  /* pp */ long _addressof_glLightModelfv;
  /* pp */ long _addressof_glLightModelx;
  /* pp */ long _addressof_glLightModelxv;
  /* pp */ long _addressof_glLightf;
  /* pp */ long _addressof_glLightfv;
  /* pp */ long _addressof_glLightx;
  /* pp */ long _addressof_glLightxv;
  /* pp */ long _addressof_glLineWidth;
  /* pp */ long _addressof_glLineWidthx;
  /* pp */ long _addressof_glLoadIdentity;
  /* pp */ long _addressof_glLoadMatrixf;
  /* pp */ long _addressof_glLoadMatrixx;
  /* pp */ long _addressof_glLoadPaletteFromModelViewMatrixOES;
  /* pp */ long _addressof_glLogicOp;
  /* pp */ long _addressof_glMaterialf;
  /* pp */ long _addressof_glMaterialfv;
  /* pp */ long _addressof_glMaterialx;
  /* pp */ long _addressof_glMaterialxv;
  /* pp */ long _addressof_glMatrixIndexPointer;
  /* pp */ long _addressof_glMatrixMode;
  /* pp */ long _addressof_glMultMatrixf;
  /* pp */ long _addressof_glMultMatrixx;
  /* pp */ long _addressof_glMultiTexCoord4f;
  /* pp */ long _addressof_glMultiTexCoord4x;
  /* pp */ long _addressof_glNormal3f;
  /* pp */ long _addressof_glNormal3x;
  /* pp */ long _addressof_glNormalPointer;
  /* pp */ long _addressof_glOrthof;
  /* pp */ long _addressof_glOrthox;
  /* pp */ long _addressof_glPixelStorei;
  /* pp */ long _addressof_glPointParameterf;
  /* pp */ long _addressof_glPointParameterfv;
  /* pp */ long _addressof_glPointParameterx;
  /* pp */ long _addressof_glPointParameterxv;
  /* pp */ long _addressof_glPointSize;
  /* pp */ long _addressof_glPointSizePointerOES;
  /* pp */ long _addressof_glPointSizex;
  /* pp */ long _addressof_glPolygonOffset;
  /* pp */ long _addressof_glPolygonOffsetx;
  /* pp */ long _addressof_glPopMatrix;
  /* pp */ long _addressof_glPushMatrix;
  /* pp */ long _addressof_glQueryMatrixxOES;
  /* pp */ long _addressof_glReadPixels;
  /* pp */ long _addressof_glReadnPixels;
  /* pp */ long _addressof_glRenderbufferStorageMultisampleAPPLE;
  /* pp */ long _addressof_glRenderbufferStorageMultisampleIMG;
  /* pp */ long _addressof_glRenderbufferStorage;
  /* pp */ long _addressof_glResolveMultisampleFramebufferAPPLE;
  /* pp */ long _addressof_glRotatef;
  /* pp */ long _addressof_glRotatex;
  /* pp */ long _addressof_glSampleCoverage;
  /* pp */ long _addressof_glSampleCoveragex;
  /* pp */ long _addressof_glScalef;
  /* pp */ long _addressof_glScalex;
  /* pp */ long _addressof_glScissor;
  /* pp */ long _addressof_glSetFenceNV;
  /* pp */ long _addressof_glShadeModel;
  /* pp */ long _addressof_glStartTilingQCOM;
  /* pp */ long _addressof_glStencilFunc;
  /* pp */ long _addressof_glStencilMask;
  /* pp */ long _addressof_glStencilOp;
  /* pp */ long _addressof_glTestFenceNV;
  /* pp */ long _addressof_glTexCoordPointer;
  /* pp */ long _addressof_glTexEnvf;
  /* pp */ long _addressof_glTexEnvfv;
  /* pp */ long _addressof_glTexEnvi;
  /* pp */ long _addressof_glTexEnviv;
  /* pp */ long _addressof_glTexEnvx;
  /* pp */ long _addressof_glTexEnvxv;
  /* pp */ long _addressof_glTexGenf;
  /* pp */ long _addressof_glTexGenfv;
  /* pp */ long _addressof_glTexGeni;
  /* pp */ long _addressof_glTexGeniv;
  /* pp */ long _addressof_glTexImage2D;
  /* pp */ long _addressof_glTexParameterf;
  /* pp */ long _addressof_glTexParameterfv;
  /* pp */ long _addressof_glTexParameteri;
  /* pp */ long _addressof_glTexParameteriv;
  /* pp */ long _addressof_glTexParameterx;
  /* pp */ long _addressof_glTexParameterxv;
  /* pp */ long _addressof_glTexStorage1D;
  /* pp */ long _addressof_glTexStorage2D;
  /* pp */ long _addressof_glTexStorage3D;
  /* pp */ long _addressof_glTexSubImage2D;
  /* pp */ long _addressof_glTextureStorage1D;
  /* pp */ long _addressof_glTextureStorage2D;
  /* pp */ long _addressof_glTextureStorage3D;
  /* pp */ long _addressof_glTranslatef;
  /* pp */ long _addressof_glTranslatex;
  /* pp */ long _addressof_glVertexPointer;
  /* pp */ long _addressof_glViewport;
  /* pp */ long _addressof_glWeightPointer;
  @Override
  protected boolean isFunctionAvailableImpl(String functionNameUsr) throws IllegalArgumentException  {
    final String functionNameBase = com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeVEN(com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeARB(functionNameUsr, true), true);
    final String addressFieldNameBase = "_addressof_" + functionNameBase;
    final int funcNamePermNum = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutationNumber(functionNameBase);
    final java.lang.reflect.Field addressField = java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<java.lang.reflect.Field>() {
        public final java.lang.reflect.Field run() {
            java.lang.reflect.Field addressField = null;
            for(int i = 0; i < funcNamePermNum; i++) {
                final String addressFieldName = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutation(addressFieldNameBase, i);
                try {
                    addressField = GLES1ProcAddressTable.class.getDeclaredField( addressFieldName );
                    addressField.setAccessible(true); // we need to read the protected value!
                    return addressField;
                } catch (NoSuchFieldException ex) { }
            }
            return null;
        } } );

    if(null==addressField) {
      // The user is calling a bogus function or one which is not
      // runtime linked
      throw new RuntimeException(
          "WARNING: Address field query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or address field is not a known " +
          "function");
    } 
    try {
      return 0 != addressField.getLong(this);
    } catch (Exception e) {
      throw new RuntimeException(
          "WARNING: Address query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or is not a known " +
          "function", e);
    }
  }
  @Override
  public long getAddressFor(String functionNameUsr) throws SecurityException, IllegalArgumentException {
    SecurityUtil.checkAllLinkPermission();
    final String functionNameBase = com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeVEN(com.jogamp.gluegen.runtime.opengl.GLNameResolver.normalizeARB(functionNameUsr, true), true);
    final String addressFieldNameBase = "_addressof_" + functionNameBase;
    final int  funcNamePermNum = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutationNumber(functionNameBase);
    final java.lang.reflect.Field addressField = java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<java.lang.reflect.Field>() {
        public final java.lang.reflect.Field run() {
            java.lang.reflect.Field addressField = null;
            for(int i = 0; i < funcNamePermNum; i++) {
                final String addressFieldName = com.jogamp.gluegen.runtime.opengl.GLNameResolver.getFuncNamePermutation(addressFieldNameBase, i);
                try {
                    addressField = GLES1ProcAddressTable.class.getDeclaredField( addressFieldName );
                    addressField.setAccessible(true); // we need to read the protected value!
                    return addressField;
                } catch (NoSuchFieldException ex) { }
            }
            return null;
        } } );

    if(null==addressField) {
      // The user is calling a bogus function or one which is not
      // runtime linked
      throw new RuntimeException(
          "WARNING: Address field query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or address field is not a known " +
          "function");
    } 
    try {
      return addressField.getLong(this);
    } catch (Exception e) {
      throw new RuntimeException(
          "WARNING: Address query failed for \"" + functionNameBase + "\"/\"" + functionNameUsr +
          "\"; it's either statically linked or is not a known " +
          "function", e);
    }
  }
} // end of class GLES1ProcAddressTable
