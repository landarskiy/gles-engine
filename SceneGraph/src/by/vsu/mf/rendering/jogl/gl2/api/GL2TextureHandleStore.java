package by.vsu.mf.rendering.jogl.gl2.api;

import java.util.HashMap;
import java.util.Map;

public class GL2TextureHandleStore {

    private Map<String, Integer> handleMap = new HashMap<>();

    public int getTextureHandle(final String name) {
        final Integer handle = this.handleMap.get(name);
        if (handle == null) {
            throw new RuntimeException("Texture with name \"" + name + "\" not loaded in the current context!");
        } else {
            return handle;
        }
    }

    public void putTextureHandle(final String name, final int handle) {
        if (this.handleMap.containsKey(name)) {
            throw new RuntimeException("Attempt to register multiple handles for texture \"" + name + "\"!");
        } else {
            this.handleMap.put(name, handle);
        }
    }

    public void clearTextureHandles() {
        this.handleMap.clear();
    }

    public void removeTextureHandle(final String name) {
        final Integer handle = this.handleMap.remove(name);
        if (handle == null) {
            throw new RuntimeException("Attempt to delete texture with name \"" + name
                    + "\" that is not loaded in the current context!");
        }
    }

}
