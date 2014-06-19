/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/JavaEmitter.java on Tue Mar 11 04:15:54 CET 2014 ----! */


package jogamp.nativewindow.windows;

import java.nio.*;

import com.jogamp.gluegen.runtime.*;
import com.jogamp.common.os.*;
import com.jogamp.common.nio.*;
import jogamp.common.os.MachineDescriptionRuntime;

import javax.media.nativewindow.util.Point;
import javax.media.nativewindow.NativeWindowException;
import jogamp.nativewindow.NWJNILibLoader;
import jogamp.nativewindow.Debug;

public class MARGINS {

  StructAccessor accessor;

  private static final int mdIdx = MachineDescriptionRuntime.getStatic().ordinal();

  private static final int[] MARGINS_size = new int[] { 16 /* ARMle_EABI */, 16 /* X86_32_UNIX */, 16 /* X86_64_UNIX */, 16 /* X86_32_MACOS */, 16 /* X86_32_WINDOWS */, 16 /* X86_64_WINDOWS */, 16 /* SPARC_32_SUNOS */  };
  private static final int[] cxLeftWidth_offset = new int[] { 0 /* ARMle_EABI */, 0 /* X86_32_UNIX */, 0 /* X86_64_UNIX */, 0 /* X86_32_MACOS */, 0 /* X86_32_WINDOWS */, 0 /* X86_64_WINDOWS */, 0 /* SPARC_32_SUNOS */ };
  private static final int[] cxRightWidth_offset = new int[] { 4 /* ARMle_EABI */, 4 /* X86_32_UNIX */, 4 /* X86_64_UNIX */, 4 /* X86_32_MACOS */, 4 /* X86_32_WINDOWS */, 4 /* X86_64_WINDOWS */, 4 /* SPARC_32_SUNOS */ };
  private static final int[] cyTopHeight_offset = new int[] { 8 /* ARMle_EABI */, 8 /* X86_32_UNIX */, 8 /* X86_64_UNIX */, 8 /* X86_32_MACOS */, 8 /* X86_32_WINDOWS */, 8 /* X86_64_WINDOWS */, 8 /* SPARC_32_SUNOS */ };
  private static final int[] cyBottomHeight_offset = new int[] { 12 /* ARMle_EABI */, 12 /* X86_32_UNIX */, 12 /* X86_64_UNIX */, 12 /* X86_32_MACOS */, 12 /* X86_32_WINDOWS */, 12 /* X86_64_WINDOWS */, 12 /* SPARC_32_SUNOS */ };

  public static int size() {
    return MARGINS_size[mdIdx];
  }

  public static MARGINS create() {
    return create(Buffers.newDirectByteBuffer(size()));
  }

  public static MARGINS create(java.nio.ByteBuffer buf) {
      return new MARGINS(buf);
  }

  MARGINS(java.nio.ByteBuffer buf) {
    accessor = new StructAccessor(buf);
  }

  public java.nio.ByteBuffer getBuffer() {
    return accessor.getBuffer();
  }

  public MARGINS setCxLeftWidth(int val) {
    accessor.setIntAt(cxLeftWidth_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
    return this;
  }

  public int getCxLeftWidth() {
    return accessor.getIntAt(cxLeftWidth_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
  }

  public MARGINS setCxRightWidth(int val) {
    accessor.setIntAt(cxRightWidth_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
    return this;
  }

  public int getCxRightWidth() {
    return accessor.getIntAt(cxRightWidth_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
  }

  public MARGINS setCyTopHeight(int val) {
    accessor.setIntAt(cyTopHeight_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
    return this;
  }

  public int getCyTopHeight() {
    return accessor.getIntAt(cyTopHeight_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
  }

  public MARGINS setCyBottomHeight(int val) {
    accessor.setIntAt(cyBottomHeight_offset[mdIdx], val, MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
    return this;
  }

  public int getCyBottomHeight() {
    return accessor.getIntAt(cyBottomHeight_offset[mdIdx], MachineDescriptionRuntime.getStatic().md.intSizeInBytes());
  }
}
