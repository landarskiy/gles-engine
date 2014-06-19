/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Tue Mar 11 04:19:32 CET 2014 ----! */

package jogamp.opengl.macosx.cgl;

import java.util.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import jogamp.opengl.*;
import com.jogamp.common.util.Function;
import jogamp.nativewindow.macosx.OSXUtil;
import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import java.nio.*;

 /**
  * Provides access to the MacOSX-specific OpenGL vendor extensions.
  * See {@link GLBase} for more information.
  */
public class CGL {

  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAAllRenderers = 1;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFADoubleBuffer = 5;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAStereo = 6;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAAuxBuffers = 7;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAColorSize = 8;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAAlphaSize = 11;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFADepthSize = 12;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAStencilSize = 13;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAAccumSize = 14;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAMinimumPolicy = 51;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAMaximumPolicy = 52;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAOffScreen = 53;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAFullScreen = 54;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFASampleBuffers = 55;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFASamples = 56;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAAuxDepthStencil = 57;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAColorFloat = 58;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAMultisample = 59;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFASupersample = 60;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFASampleAlpha = 61;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFARendererID = 70;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFASingleRenderer = 71;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFANoRecovery = 72;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAAccelerated = 73;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAClosestPolicy = 74;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFARobust = 75;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFABackingStore = 76;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAMPSafe = 78;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAWindow = 80;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAMultiScreen = 81;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFACompliant = 83;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAScreenMask = 84;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAPixelBuffer = 90;
  /** Part of NSOpenGLPixelFormatAttribute<br>Defined as part of enum type "NSOpenGLPixelFormatAttribute" */
  public static final int NSOpenGLPFAVirtualScreenCount = 128;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSwapRectangle = 200;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSwapInterval = 222;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPDispatchTableSize = 224;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPClientStorage = 226;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSurfaceTexture = 228;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSurfaceOrder = 235;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSurfaceOpacity = 236;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSurfaceBackingSize = 304;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPSurfaceSurfaceVolatile = 306;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPReclaimResources = 308;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPCurrentRendererID = 309;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPGPUVertexProcessing = 310;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPGPUFragmentProcessing = 311;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPHasDrawable = 314;
  /** Part of CGLContextParameter<br>Defined as part of enum type "CGLContextParameter" */
  public static final int kCGLCPMPSwapsInFlight = 315;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAllRenderers = 1;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFADoubleBuffer = 5;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAStereo = 6;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAuxBuffers = 7;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAColorSize = 8;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAlphaSize = 11;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFADepthSize = 12;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAStencilSize = 13;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAccumSize = 14;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAMinimumPolicy = 51;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAMaximumPolicy = 52;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAOffScreen = 53;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAFullScreen = 54;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFASampleBuffers = 55;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFASamples = 56;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAuxDepthStencil = 57;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAColorFloat = 58;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAMultisample = 59;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFASupersample = 60;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFASampleAlpha = 61;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFARendererID = 70;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFASingleRenderer = 71;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFANoRecovery = 72;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAccelerated = 73;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAClosestPolicy = 74;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFARobust = 75;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFABackingStore = 76;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAMPSafe = 78;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAWindow = 80;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAMultiScreen = 81;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFACompliant = 83;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFADisplayMask = 84;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAPBuffer = 90;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFARemotePBuffer = 91;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAAcceleratedCompute = 97;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAOpenGLProfile = 99;
  /** Part of CGLPixelFormatAttribute<br>Defined as part of enum type "CGLPixelFormatAttribute" */
  public static final int kCGLPFAVirtualScreenCount = 128;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLNoError = 0;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadAttribute = 10000;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadProperty = 10001;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadPixelFormat = 10002;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadRendererInfo = 10003;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadContext = 10004;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadDrawable = 10005;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadDisplay = 10006;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadState = 10007;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadValue = 10008;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadMatch = 10009;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadEnumeration = 10010;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadOffScreen = 10011;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadFullScreen = 10012;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadWindow = 10013;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadAddress = 10014;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadCodeModule = 10015;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadAlloc = 10016;
  /** Part of CGLError<br>Defined as part of enum type "CGLError" */
  public static final int kCGLBadConnection = 10017;
  /** Part of CGLOpenGLProfile<br>Defined as part of enum type "CGLOpenGLProfile" */
  public static final int kCGLOGLPVersion_Legacy = 4096;
  /** Part of CGLOpenGLProfile<br>Defined as part of enum type "CGLOpenGLProfile" */
  public static final int kCGLOGLPVersion_GL3_Core = 12800;
  /** Part of CGLOpenGLProfile<br>Defined as part of enum type "CGLOpenGLProfile" */
  public static final int kCGLOGLPVersion_GL4_Core = 16640;

  /** Interface to C language function: <br> <code> CGLError CGLChoosePixelFormat(CGLPixelFormatAttribute *  attribs, CGLPixelFormatObj *  pix, GLint *  npix); </code> 
      @param attribs a direct only {@link java.nio.IntBuffer}
      @param pix a direct only {@link com.jogamp.common.nio.PointerBuffer}
      @param npix a direct only {@link java.nio.IntBuffer}   */
  public static int CGLChoosePixelFormat(IntBuffer attribs, PointerBuffer pix, IntBuffer npix)  {

    if (!Buffers.isDirect(attribs))
      throw new GLException("Argument \"attribs\" is not a direct buffer");
    if (!Buffers.isDirect(pix))
      throw new GLException("Argument \"pix\" is not a direct buffer");
    if (!Buffers.isDirect(npix))
      throw new GLException("Argument \"npix\" is not a direct buffer");
        return CGLChoosePixelFormat0(attribs, Buffers.getDirectBufferByteOffset(attribs), pix != null ? pix.getBuffer() : null, Buffers.getDirectBufferByteOffset(pix), npix, Buffers.getDirectBufferByteOffset(npix));
  }

  /** Entry point to C language function: <code> CGLError CGLChoosePixelFormat(CGLPixelFormatAttribute *  attribs, CGLPixelFormatObj *  pix, GLint *  npix); </code> 
      @param attribs a direct only {@link java.nio.IntBuffer}
      @param pix a direct only {@link com.jogamp.common.nio.PointerBuffer}
      @param npix a direct only {@link java.nio.IntBuffer}   */
  private static native int CGLChoosePixelFormat0(Object attribs, int attribs_byte_offset, Object pix, int pix_byte_offset, Object npix, int npix_byte_offset);

  /** Interface to C language function: <br> <code> CGLError CGLCopyContext(CGLContextObj src, CGLContextObj dst, int mask); </code>    */
  public static native int CGLCopyContext(long src, long dst, int mask);

  /** Interface to C language function: <br> <code> CGLError CGLCreateContext(CGLPixelFormatObj pix, CGLContextObj share, CGLContextObj *  ctx); </code> 
      @param ctx a direct only {@link com.jogamp.common.nio.PointerBuffer}   */
  public static int CGLCreateContext(long pix, long share, PointerBuffer ctx)  {

    if (!Buffers.isDirect(ctx))
      throw new GLException("Argument \"ctx\" is not a direct buffer");
        return CGLCreateContext0(pix, share, ctx != null ? ctx.getBuffer() : null, Buffers.getDirectBufferByteOffset(ctx));
  }

  /** Entry point to C language function: <code> CGLError CGLCreateContext(CGLPixelFormatObj pix, CGLContextObj share, CGLContextObj *  ctx); </code> 
      @param ctx a direct only {@link com.jogamp.common.nio.PointerBuffer}   */
  private static native int CGLCreateContext0(long pix, long share, Object ctx, int ctx_byte_offset);

  /** Interface to C language function: <br> <code> CGLError CGLCreatePBuffer(GLsizei width, GLsizei height, GLenum target, GLenum internalFormat, GLint max_level, CGLPBufferObj *  pbuffer); </code> 
      @param pbuffer a direct only {@link com.jogamp.common.nio.PointerBuffer}   */
  public static int CGLCreatePBuffer(int width, int height, int target, int internalFormat, int max_level, PointerBuffer pbuffer)  {

    if (!Buffers.isDirect(pbuffer))
      throw new GLException("Argument \"pbuffer\" is not a direct buffer");
        return CGLCreatePBuffer0(width, height, target, internalFormat, max_level, pbuffer != null ? pbuffer.getBuffer() : null, Buffers.getDirectBufferByteOffset(pbuffer));
  }

  /** Entry point to C language function: <code> CGLError CGLCreatePBuffer(GLsizei width, GLsizei height, GLenum target, GLenum internalFormat, GLint max_level, CGLPBufferObj *  pbuffer); </code> 
      @param pbuffer a direct only {@link com.jogamp.common.nio.PointerBuffer}   */
  private static native int CGLCreatePBuffer0(int width, int height, int target, int internalFormat, int max_level, Object pbuffer, int pbuffer_byte_offset);

  /** Interface to C language function: <br> <code> CGLError CGLDestroyContext(CGLContextObj ctx); </code>    */
  public static native int CGLDestroyContext(long ctx);

  /** Interface to C language function: <br> <code> CGLError CGLDestroyPBuffer(CGLPBufferObj pbuffer); </code>    */
  public static native int CGLDestroyPBuffer(long pbuffer);

  /** Interface to C language function: <br> <code> CGLError CGLDestroyPixelFormat(CGLPixelFormatObj pix); </code>    */
  public static native int CGLDestroyPixelFormat(long pix);

  /** Interface to C language function: <br> <code> CGLError CGLFlushDrawable(CGLContextObj ctx); </code>    */
  public static native int CGLFlushDrawable(long ctx);

  /** Interface to C language function: <br> <code> CGLContextObj CGLGetCurrentContext(void); </code>    */
  public static native long CGLGetCurrentContext();

  /** Interface to C language function: <br> <code> CGLPixelFormatObj CGLGetPixelFormat(CGLContextObj ctx); </code>    */
  public static native long CGLGetPixelFormat(long ctx);

  /** Interface to C language function: <br> <code> CGLShareGroupObj CGLGetShareGroup(CGLContextObj ctx); </code>    */
  public static native long CGLGetShareGroup(long ctx);

  /** Interface to C language function: <br> <code> CGLError CGLLockContext(CGLContextObj ctx); </code>    */
  public static native int CGLLockContext(long ctx);

  /** Interface to C language function: <br> <code> void CGLQueryPixelFormat(CGLPixelFormatObj fmt, int *  iattrs, int niattrs, int *  ivalues); </code> 
      @param iattrs a direct only {@link java.nio.IntBuffer}
      @param ivalues a direct only {@link java.nio.IntBuffer}   */
  public static void CGLQueryPixelFormat(long fmt, IntBuffer iattrs, int niattrs, IntBuffer ivalues)  {

    if (!Buffers.isDirect(iattrs))
      throw new GLException("Argument \"iattrs\" is not a direct buffer");
    if (!Buffers.isDirect(ivalues))
      throw new GLException("Argument \"ivalues\" is not a direct buffer");
        CGLQueryPixelFormat0(fmt, iattrs, Buffers.getDirectBufferByteOffset(iattrs), niattrs, ivalues, Buffers.getDirectBufferByteOffset(ivalues));
  }

  /** Entry point to C language function: <code> void CGLQueryPixelFormat(CGLPixelFormatObj fmt, int *  iattrs, int niattrs, int *  ivalues); </code> 
      @param iattrs a direct only {@link java.nio.IntBuffer}
      @param ivalues a direct only {@link java.nio.IntBuffer}   */
  private static native void CGLQueryPixelFormat0(long fmt, Object iattrs, int iattrs_byte_offset, int niattrs, Object ivalues, int ivalues_byte_offset);

  /** Interface to C language function: <br> <code> void CGLReleaseContext(CGLContextObj ctx); </code>    */
  public static native void CGLReleaseContext(long ctx);

  /** Interface to C language function: <br> <code> CGLError CGLSetCurrentContext(CGLContextObj ctx); </code>    */
  public static native int CGLSetCurrentContext(long ctx);

  /** Interface to C language function: <br> <code> CGLError CGLSetPBuffer(CGLContextObj ctx, CGLPBufferObj pbuffer, GLenum face, GLint level, GLint screen); </code>    */
  public static native int CGLSetPBuffer(long ctx, long pbuffer, int face, int level, int screen);

  /** Interface to C language function: <br> <code> CGLError CGLSetParameter(CGLContextObj ctx, CGLContextParameter pname, const int *  params); </code> 
      @param pname valid values are: <code>kCGLCPSwapRectangle, kCGLCPSwapInterval, kCGLCPDispatchTableSize, kCGLCPClientStorage, kCGLCPSurfaceTexture, kCGLCPSurfaceOrder, kCGLCPSurfaceOpacity, kCGLCPSurfaceBackingSize, kCGLCPSurfaceSurfaceVolatile, kCGLCPReclaimResources, kCGLCPCurrentRendererID, kCGLCPGPUVertexProcessing, kCGLCPGPUFragmentProcessing, kCGLCPHasDrawable, kCGLCPMPSwapsInFlight</code>

      @param params a direct only {@link java.nio.IntBuffer}   */
  public static int CGLSetParameter(long ctx, int pname, IntBuffer params)  {

    if (!Buffers.isDirect(params))
      throw new GLException("Argument \"params\" is not a direct buffer");
        return CGLSetParameter0(ctx, pname, params, Buffers.getDirectBufferByteOffset(params));
  }

  /** Entry point to C language function: <code> CGLError CGLSetParameter(CGLContextObj ctx, CGLContextParameter pname, const int *  params); </code> 
      @param pname valid values are: <code>kCGLCPSwapRectangle, kCGLCPSwapInterval, kCGLCPDispatchTableSize, kCGLCPClientStorage, kCGLCPSurfaceTexture, kCGLCPSurfaceOrder, kCGLCPSurfaceOpacity, kCGLCPSurfaceBackingSize, kCGLCPSurfaceSurfaceVolatile, kCGLCPReclaimResources, kCGLCPCurrentRendererID, kCGLCPGPUVertexProcessing, kCGLCPGPUFragmentProcessing, kCGLCPHasDrawable, kCGLCPMPSwapsInFlight</code>

      @param params a direct only {@link java.nio.IntBuffer}   */
  private static native int CGLSetParameter0(long ctx, int pname, Object params, int params_byte_offset);

  /** Interface to C language function: <br> <code> CGLError CGLUnlockContext(CGLContextObj ctx); </code>    */
  public static native int CGLUnlockContext(long ctx);

  /** Interface to C language function: <br> <code> Bool clearCurrentContext(NSOpenGLContext *  ctx); </code>    */
  public static native boolean clearCurrentContext(long ctx);

  /** Interface to C language function: <br> <code> void clearDrawable(NSOpenGLContext *  ctx); </code>    */
  public static native void clearDrawable(long ctx);

  /** Interface to C language function: <br> <code> void copyContext(NSOpenGLContext *  dest, NSOpenGLContext *  src, int mask); </code>    */
  public static native void copyContext(long dest, long src, int mask);

  /** Interface to C language function: <br> <code> NSOpenGLContext *  createContext(NSOpenGLContext *  shareContext, NSView *  nsView, Bool incompleteView, NSOpenGLPixelFormat *  pixelFormat, Bool opaque, int *  viewNotReady); </code> 
      @param viewNotReady a direct only {@link java.nio.IntBuffer}   */
  public static long createContext(long shareContext, long nsView, boolean incompleteView, long pixelFormat, boolean opaque, IntBuffer viewNotReady)  {

    if (!Buffers.isDirect(viewNotReady))
      throw new GLException("Argument \"viewNotReady\" is not a direct buffer");
        return createContext0(shareContext, nsView, incompleteView, pixelFormat, opaque, viewNotReady, Buffers.getDirectBufferByteOffset(viewNotReady));
  }

  /** Entry point to C language function: <code> NSOpenGLContext *  createContext(NSOpenGLContext *  shareContext, NSView *  nsView, Bool incompleteView, NSOpenGLPixelFormat *  pixelFormat, Bool opaque, int *  viewNotReady); </code> 
      @param viewNotReady a direct only {@link java.nio.IntBuffer}   */
  private static native long createContext0(long shareContext, long nsView, boolean incompleteView, long pixelFormat, boolean opaque, Object viewNotReady, int viewNotReady_byte_offset);

  /** Interface to C language function: <br> <code> NSOpenGLLayer *  createNSOpenGLLayer(NSOpenGLContext *  ctx, int gl3ShaderProgramName, NSOpenGLPixelFormat *  fmt, NSOpenGLPixelBuffer *  p, uint32_t texID, Bool opaque, int texWidth, int texHeight); </code>    */
  private static native long createNSOpenGLLayerImpl(long ctx, int gl3ShaderProgramName, long fmt, long p, int texID, boolean opaque, int texWidth, int texHeight);

  /** Interface to C language function: <br> <code> NSOpenGLPixelBuffer *  createPBuffer(int renderTarget, int internalFormat, int width, int height); </code>    */
  public static native long createPBuffer(int renderTarget, int internalFormat, int width, int height);

  /** Interface to C language function: <br> <code> NSOpenGLPixelFormat *  createPixelFormat(int *  iattrs, int niattrs, int *  ivalues); </code> 
      @param iattrs a direct only {@link java.nio.IntBuffer}
      @param ivalues a direct only {@link java.nio.IntBuffer}   */
  public static long createPixelFormat(IntBuffer iattrs, int niattrs, IntBuffer ivalues)  {

    if (!Buffers.isDirect(iattrs))
      throw new GLException("Argument \"iattrs\" is not a direct buffer");
    if (!Buffers.isDirect(ivalues))
      throw new GLException("Argument \"ivalues\" is not a direct buffer");
        return createPixelFormat0(iattrs, Buffers.getDirectBufferByteOffset(iattrs), niattrs, ivalues, Buffers.getDirectBufferByteOffset(ivalues));
  }

  /** Entry point to C language function: <code> NSOpenGLPixelFormat *  createPixelFormat(int *  iattrs, int niattrs, int *  ivalues); </code> 
      @param iattrs a direct only {@link java.nio.IntBuffer}
      @param ivalues a direct only {@link java.nio.IntBuffer}   */
  private static native long createPixelFormat0(Object iattrs, int iattrs_byte_offset, int niattrs, Object ivalues, int ivalues_byte_offset);

  /** Interface to C language function: <br> <code> Bool deleteContext(NSOpenGLContext *  ctx, Bool releaseOnMainThread); </code>    */
  public static native boolean deleteContext(long ctx, boolean releaseOnMainThread);

  /** Interface to C language function: <br> <code> void deletePixelFormat(NSOpenGLPixelFormat *  fmt); </code>    */
  public static native void deletePixelFormat(long fmt);

  /** Interface to C language function: <br> <code> Bool destroyPBuffer(NSOpenGLPixelBuffer *  pBuffer); </code>    */
  public static native boolean destroyPBuffer(long pBuffer);

  /** Interface to C language function: <br> <code> Bool flushBuffer(NSOpenGLContext *  ctx); </code>    */
  public static native boolean flushBuffer(long ctx);

  /** Interface to C language function: <br> <code> CGLContextObj getCGLContext(NSOpenGLContext *  ctx); </code>    */
  public static native long getCGLContext(long ctx);

  /** Interface to C language function: <br> <code> NSOpenGLContext *  getCurrentContext(void); </code>    */
  public static native long getCurrentContext();

  /** Interface to C language function: <br> <code> NSView *  getNSView(NSOpenGLContext *  ctx); </code>    */
  public static native long getNSView(long ctx);

  /** Interface to C language function: <br> <code> void *  getProcAddress(const char *  procName); </code>    */
  public static native long getProcAddress(String procName);

  /** Interface to C language function: <br> <code> Bool isNSOpenGLPixelBuffer(uint64_t object); </code>    */
  public static native boolean isNSOpenGLPixelBuffer(long object);

  /** Interface to C language function: <br> <code> Bool makeCurrentContext(NSOpenGLContext *  ctx); </code>    */
  public static native boolean makeCurrentContext(long ctx);

  /** Interface to C language function: <br> <code> void queryPixelFormat(NSOpenGLPixelFormat *  fmt, int *  iattrs, int niattrs, int *  ivalues); </code> 
      @param iattrs a direct only {@link java.nio.IntBuffer}
      @param ivalues a direct only {@link java.nio.IntBuffer}   */
  public static void queryPixelFormat(long fmt, IntBuffer iattrs, int niattrs, IntBuffer ivalues)  {

    if (!Buffers.isDirect(iattrs))
      throw new GLException("Argument \"iattrs\" is not a direct buffer");
    if (!Buffers.isDirect(ivalues))
      throw new GLException("Argument \"ivalues\" is not a direct buffer");
        queryPixelFormat0(fmt, iattrs, Buffers.getDirectBufferByteOffset(iattrs), niattrs, ivalues, Buffers.getDirectBufferByteOffset(ivalues));
  }

  /** Entry point to C language function: <code> void queryPixelFormat(NSOpenGLPixelFormat *  fmt, int *  iattrs, int niattrs, int *  ivalues); </code> 
      @param iattrs a direct only {@link java.nio.IntBuffer}
      @param ivalues a direct only {@link java.nio.IntBuffer}   */
  private static native void queryPixelFormat0(long fmt, Object iattrs, int iattrs_byte_offset, int niattrs, Object ivalues, int ivalues_byte_offset);

  /** Interface to C language function: <br> <code> void releaseNSOpenGLLayer(NSOpenGLLayer *  glLayer); </code>    */
  private static native void releaseNSOpenGLLayerImpl(long glLayer);

  /** Interface to C language function: <br> <code> void resetGammaRamp(); </code>    */
  public static native void resetGammaRamp();

  /** Interface to C language function: <br> <code> void setContextOpacity(NSOpenGLContext *  ctx, int opacity); </code>    */
  public static native void setContextOpacity(long ctx, int opacity);

  /** Interface to C language function: <br> <code> void setContextPBuffer(NSOpenGLContext *  ctx, NSOpenGLPixelBuffer *  pBuffer); </code>    */
  public static native void setContextPBuffer(long ctx, long pBuffer);

  /** Interface to C language function: <br> <code> void setContextTextureImageToPBuffer(NSOpenGLContext *  ctx, NSOpenGLPixelBuffer *  pBuffer, GLenum colorBuffer); </code>    */
  public static native void setContextTextureImageToPBuffer(long ctx, long pBuffer, int colorBuffer);

  /** Interface to C language function: <br> <code> void setContextView(NSOpenGLContext *  ctx, NSView *  view); </code>    */
  public static native void setContextView(long ctx, long view);

  /** Interface to C language function: <br> <code> Bool setGammaRamp(int tableSize, float *  redRamp, float *  greenRamp, float *  blueRamp); </code> 
      @param redRamp a direct only {@link java.nio.FloatBuffer}
      @param greenRamp a direct only {@link java.nio.FloatBuffer}
      @param blueRamp a direct only {@link java.nio.FloatBuffer}   */
  public static boolean setGammaRamp(int tableSize, FloatBuffer redRamp, FloatBuffer greenRamp, FloatBuffer blueRamp)  {

    if (!Buffers.isDirect(redRamp))
      throw new GLException("Argument \"redRamp\" is not a direct buffer");
    if (!Buffers.isDirect(greenRamp))
      throw new GLException("Argument \"greenRamp\" is not a direct buffer");
    if (!Buffers.isDirect(blueRamp))
      throw new GLException("Argument \"blueRamp\" is not a direct buffer");
        return setGammaRamp0(tableSize, redRamp, Buffers.getDirectBufferByteOffset(redRamp), greenRamp, Buffers.getDirectBufferByteOffset(greenRamp), blueRamp, Buffers.getDirectBufferByteOffset(blueRamp));
  }

  /** Entry point to C language function: <code> Bool setGammaRamp(int tableSize, float *  redRamp, float *  greenRamp, float *  blueRamp); </code> 
      @param redRamp a direct only {@link java.nio.FloatBuffer}
      @param greenRamp a direct only {@link java.nio.FloatBuffer}
      @param blueRamp a direct only {@link java.nio.FloatBuffer}   */
  private static native boolean setGammaRamp0(int tableSize, Object redRamp, int redRamp_byte_offset, Object greenRamp, int greenRamp_byte_offset, Object blueRamp, int blueRamp_byte_offset);

  /** Interface to C language function: <br> <code> void setNSOpenGLLayerEnabled(NSOpenGLLayer *  layer, Bool enable); </code>    */
  private static native void setNSOpenGLLayerEnabledImpl(long layer, boolean enable);

  /** Interface to C language function: <br> <code> void setNSOpenGLLayerNeedsDisplayFBO(NSOpenGLLayer *  layer, uint32_t texID); </code>    */
  public static native void setNSOpenGLLayerNeedsDisplayFBO(long layer, int texID);

  /** Interface to C language function: <br> <code> void setNSOpenGLLayerNeedsDisplayPBuffer(NSOpenGLLayer *  layer, NSOpenGLPixelBuffer *  p); </code>    */
  public static native void setNSOpenGLLayerNeedsDisplayPBuffer(long layer, long p);

  /** Interface to C language function: <br> <code> void setNSOpenGLLayerSwapInterval(NSOpenGLLayer *  layer, int interval); </code>    */
  public static native void setNSOpenGLLayerSwapInterval(long layer, int interval);

  /** Interface to C language function: <br> <code> void setSwapInterval(NSOpenGLContext *  ctx, int interval); </code>    */
  public static native void setSwapInterval(long ctx, int interval);

  /** Interface to C language function: <br> <code> void updateContext(NSOpenGLContext *  ctx); </code>    */
  public static native void updateContext(long ctx);

  /** Interface to C language function: <br> <code> Bool updateContextNeedsUpdate(void *  updater); </code>    */
  public static native boolean updateContextNeedsUpdate(long updater);

  /** Interface to C language function: <br> <code> void *  updateContextRegister(NSOpenGLContext *  ctx, NSView *  view); </code>    */
  public static native long updateContextRegister(long ctx, long view);

  /** Interface to C language function: <br> <code> void updateContextUnregister(void *  updater); </code>    */
  public static native void updateContextUnregister(long updater);

  /** Interface to C language function: <br> <code> void waitUntilNSOpenGLLayerIsReady(NSOpenGLLayer *  layer, long to_micros); </code>    */
  public static native void waitUntilNSOpenGLLayerIsReady(long layer, long to_micros);


  // --- Begin CustomJavaCode .cfg declarations
  
  /**
   * Creates the NSOpenGLLayer for FBO/PBuffer w/ optional GL3 shader program
   * <p>
   * The NSOpenGLLayer will immediatly create a OpenGL context sharing the given ctx,
   * which will be used to render the texture offthread.
   * </p>
   * <p>
   * The NSOpenGLLayer starts in enabled mode, 
   * you may enable/disable it via {@link #setNSOpenGLLayerEnabled(long, boolean)}.
   * </p>
   */
  public static long createNSOpenGLLayer(final long ctx, final int gl3ShaderProgramName, final long fmt, final long p, 
                                         final int texID, final boolean opaque, final int texWidth, final int texHeight) {
     return createNSOpenGLLayerImpl(ctx, gl3ShaderProgramName, fmt, p, texID, opaque, texWidth, texHeight);
  }
  
  /**
   * Enable or disable NSOpenGLLayer. 
   *
   * <p>
   * If disabled, the NSOpenGLLayer will not be displayed, i.e. rendered.
   * </p>
   */
  public static void setNSOpenGLLayerEnabled(final long nsOpenGLLayer, final boolean enable) {
    setNSOpenGLLayerEnabledImpl(nsOpenGLLayer, enable);
  }
  
  /**
   * Releases the NSOpenGLLayer
   */
  public static void releaseNSOpenGLLayer(final long nsOpenGLLayer) {
    releaseNSOpenGLLayerImpl(nsOpenGLLayer);
  }
  
  // ---- End CustomJavaCode .cfg declarations

} // end of class CGL
