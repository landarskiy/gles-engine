/* !---- DO NOT EDIT: This file autogenerated by com/jogamp/gluegen/opengl/GLEmitter.java on Tue Mar 11 04:18:52 CET 2014 ----! */

package jogamp.opengl.egl;

import java.util.*;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import jogamp.opengl.*;
import com.jogamp.gluegen.runtime.ProcAddressTable;
import com.jogamp.common.util.SecurityUtil;

/**
 * This table is a cache of pointers to the dynamically-linkable C library.
 * @see ProcAddressTable
 */
public final class EGLExtProcAddressTable extends ProcAddressTable {


  public EGLExtProcAddressTable(){ super(); }

  public EGLExtProcAddressTable(com.jogamp.gluegen.runtime.FunctionAddressResolver resolver){ super(resolver); }

  /* pp */ long _addressof_eglClientWaitSyncKHR;
  /* pp */ long _addressof_eglClientWaitSyncNV;
  /* pp */ long _addressof_eglCreateDRMImageMESA;
  /* pp */ long _addressof_eglCreateFenceSyncNV;
  /* pp */ long _addressof_eglCreateImageKHR;
  /* pp */ long _addressof_eglCreatePixmapSurfaceHI;
  /* pp */ long _addressof_eglCreateStreamFromFileDescriptorKHR;
  /* pp */ long _addressof_eglCreateStreamKHR;
  /* pp */ long _addressof_eglCreateStreamProducerSurfaceKHR;
  /* pp */ long _addressof_eglCreateSyncKHR;
  /* pp */ long _addressof_eglDestroyImageKHR;
  /* pp */ long _addressof_eglDestroyStreamKHR;
  /* pp */ long _addressof_eglDestroySyncKHR;
  /* pp */ long _addressof_eglDestroySyncNV;
  /* pp */ long _addressof_eglDupNativeFenceFDANDROID;
  /* pp */ long _addressof_eglExportDRMImageMESA;
  /* pp */ long _addressof_eglFenceNV;
  /* pp */ long _addressof_eglGetStreamFileDescriptorKHR;
  /* pp */ long _addressof_eglGetSyncAttribKHR;
  /* pp */ long _addressof_eglGetSyncAttribNV;
  /* pp */ long _addressof_eglGetSystemTimeFrequencyNV;
  /* pp */ long _addressof_eglGetSystemTimeNV;
  /* pp */ long _addressof_eglLockSurfaceKHR;
  /* pp */ long _addressof_eglPostSubBufferNV;
  /* pp */ long _addressof_eglQueryNativeDisplayNV;
  /* pp */ long _addressof_eglQueryNativePixmapNV;
  /* pp */ long _addressof_eglQueryNativeWindowNV;
  /* pp */ long _addressof_eglQueryStreamKHR;
  /* pp */ long _addressof_eglQueryStreamTimeKHR;
  /* pp */ long _addressof_eglQueryStreamu64KHR;
  /* pp */ long _addressof_eglQuerySurfacePointerANGLE;
  /* pp */ long _addressof_eglSignalSyncKHR;
  /* pp */ long _addressof_eglSignalSyncNV;
  /* pp */ long _addressof_eglStreamAttribKHR;
  /* pp */ long _addressof_eglStreamConsumerAcquireKHR;
  /* pp */ long _addressof_eglStreamConsumerGLTextureExternalKHR;
  /* pp */ long _addressof_eglStreamConsumerReleaseKHR;
  /* pp */ long _addressof_eglSwapBuffersWithDamageEXT;
  /* pp */ long _addressof_eglUnlockSurfaceKHR;
  /* pp */ long _addressof_eglWaitSyncKHR;
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
                    addressField = EGLExtProcAddressTable.class.getDeclaredField( addressFieldName );
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
                    addressField = EGLExtProcAddressTable.class.getDeclaredField( addressFieldName );
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
} // end of class EGLExtProcAddressTable
