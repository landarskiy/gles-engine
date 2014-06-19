/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/JavaEmitter.java on Tue Mar 11 04:15:50 CET 2014 ----! */


package jogamp.nativewindow.x11;

import java.nio.*;

import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import jogamp.common.os.MachineDescriptionRuntime;

import java.nio.*;
import java.util.*;
import javax.media.nativewindow.util.Point;

public class XRenderPictFormat {

  StructAccessor accessor;

  private static final int mdIdx = MachineDescriptionRuntime.getStatic().ordinal();

  private static final int[] XRenderPictFormat_size = new int[] { 32 /* ARMle_EABI */, 32 /* X86_32_UNIX */, 40 /* X86_64_UNIX */, 32 /* X86_32_MACOS */, 32 /* X86_32_WINDOWS */, 40 /* X86_64_WINDOWS */, 32 /* SPARC_32_SUNOS */  };
  private static final int[] id_offset = new int[] { 0 /* ARMle_EABI */, 0 /* X86_32_UNIX */, 0 /* X86_64_UNIX */, 0 /* X86_32_MACOS */, 0 /* X86_32_WINDOWS */, 0 /* X86_64_WINDOWS */, 0 /* SPARC_32_SUNOS */ };
  private static final int[] type_offset = new int[] { 4 /* ARMle_EABI */, 4 /* X86_32_UNIX */, 8 /* X86_64_UNIX */, 4 /* X86_32_MACOS */, 4 /* X86_32_WINDOWS */, 8 /* X86_64_WINDOWS */, 4 /* SPARC_32_SUNOS */ };
  private static final int[] depth_offset = new int[] { 8 /* ARMle_EABI */, 8 /* X86_32_UNIX */, 12 /* X86_64_UNIX */, 8 /* X86_32_MACOS */, 8 /* X86_32_WINDOWS */, 12 /* X86_64_WINDOWS */, 8 /* SPARC_32_SUNOS */ };
  private static final int[] direct_offset = new int[] { 12 /* ARMle_EABI */, 12 /* X86_32_UNIX */, 16 /* X86_64_UNIX */, 12 /* X86_32_MACOS */, 12 /* X86_32_WINDOWS */, 16 /* X86_64_WINDOWS */, 12 /* SPARC_32_SUNOS */ };
  private static final int[] direct_size = new int[] { 16 /* ARMle_EABI */, 16 /* X86_32_UNIX */, 16 /* X86_64_UNIX */, 16 /* X86_32_MACOS */, 16 /* X86_32_WINDOWS */, 16 /* X86_64_WINDOWS */, 16 /* SPARC_32_SUNOS */  };
  private static final int[] colormap_offset = new int[] { 28 /* ARMle_EABI */, 28 /* X86_32_UNIX */, 32 /* X86_64_UNIX */, 28 /* X86_32_MACOS */, 28 /* X86_32_WINDOWS */, 32 /* X86_64_WINDOWS */, 28 /* SPARC_32_SUNOS */ };

  public static int size() {
    return XRenderPictFormat_size[mdIdx];
  }

  public static XRenderPictFormat create() {
    return create(Buffers.newDirectByteBuffer(size()));
  }

  public static XRenderPictFormat create(java.nio.ByteBuffer buf) {
      return new XRenderPictFormat(buf);
  }

  XRenderPictFormat(java.nio.ByteBuffer buf) {
    accessor = new StructAccessor(buf);
  }

  public java.nio.ByteBuffer getBuffer() {
    return accessor.getBuffer();
  }

  public XRenderPictFormat setId(long val) {
    accessor.setLongAt(id_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.pointerSizeInBytes());
    return this;
  }

  public long getId() {
    return accessor.getLongAt(id_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.pointerSizeInBytes());
  }

  public XRenderPictFormat setType(int val) {
    accessor.setIntAt(type_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
    return this;
  }

  public int getType() {
    return accessor.getIntAt(type_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
  }

  public XRenderPictFormat setDepth(int val) {
    accessor.setIntAt(depth_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
    return this;
  }

  public int getDepth() {
    return accessor.getIntAt(depth_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
  }

  public XRenderDirectFormat getDirect() {
    return XRenderDirectFormat.create( accessor.slice( direct_offset[mdIdx], direct_size[mdIdx] ) );
 }

  public XRenderPictFormat setColormap(long val) {
    accessor.setLongAt(colormap_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.pointerSizeInBytes());
    return this;
  }

  public long getColormap() {
    return accessor.getLongAt(colormap_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.pointerSizeInBytes());
  }
}
