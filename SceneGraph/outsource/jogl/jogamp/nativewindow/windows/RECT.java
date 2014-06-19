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

public class RECT {

  StructAccessor accessor;

  private static final int mdIdx = MachineDescriptionRuntime.getStatic().ordinal();

  private static final int[] RECT_size = new int[] { 16 /* ARMle_EABI */, 16 /* X86_32_UNIX */, 16 /* X86_64_UNIX */, 16 /* X86_32_MACOS */, 16 /* X86_32_WINDOWS */, 16 /* X86_64_WINDOWS */, 16 /* SPARC_32_SUNOS */  };
  private static final int[] left_offset = new int[] { 0 /* ARMle_EABI */, 0 /* X86_32_UNIX */, 0 /* X86_64_UNIX */, 0 /* X86_32_MACOS */, 0 /* X86_32_WINDOWS */, 0 /* X86_64_WINDOWS */, 0 /* SPARC_32_SUNOS */ };
  private static final int[] top_offset = new int[] { 4 /* ARMle_EABI */, 4 /* X86_32_UNIX */, 4 /* X86_64_UNIX */, 4 /* X86_32_MACOS */, 4 /* X86_32_WINDOWS */, 4 /* X86_64_WINDOWS */, 4 /* SPARC_32_SUNOS */ };
  private static final int[] right_offset = new int[] { 8 /* ARMle_EABI */, 8 /* X86_32_UNIX */, 8 /* X86_64_UNIX */, 8 /* X86_32_MACOS */, 8 /* X86_32_WINDOWS */, 8 /* X86_64_WINDOWS */, 8 /* SPARC_32_SUNOS */ };
  private static final int[] bottom_offset = new int[] { 12 /* ARMle_EABI */, 12 /* X86_32_UNIX */, 12 /* X86_64_UNIX */, 12 /* X86_32_MACOS */, 12 /* X86_32_WINDOWS */, 12 /* X86_64_WINDOWS */, 12 /* SPARC_32_SUNOS */ };

  public static int size() {
    return RECT_size[mdIdx];
  }

  public static RECT create() {
    return create(Buffers.newDirectByteBuffer(size()));
  }

  public static RECT create(java.nio.ByteBuffer buf) {
      return new RECT(buf);
  }

  RECT(java.nio.ByteBuffer buf) {
    accessor = new StructAccessor(buf);
  }

  public java.nio.ByteBuffer getBuffer() {
    return accessor.getBuffer();
  }

  public RECT setLeft(int val) {
    accessor.setIntAt(left_offset[mdIdx], val);
    return this;
  }

  public int getLeft() {
    return accessor.getIntAt(left_offset[mdIdx]);
  }

  public RECT setTop(int val) {
    accessor.setIntAt(top_offset[mdIdx], val);
    return this;
  }

  public int getTop() {
    return accessor.getIntAt(top_offset[mdIdx]);
  }

  public RECT setRight(int val) {
    accessor.setIntAt(right_offset[mdIdx], val);
    return this;
  }

  public int getRight() {
    return accessor.getIntAt(right_offset[mdIdx]);
  }

  public RECT setBottom(int val) {
    accessor.setIntAt(bottom_offset[mdIdx], val);
    return this;
  }

  public int getBottom() {
    return accessor.getIntAt(bottom_offset[mdIdx]);
  }
}
