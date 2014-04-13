package by.vsu.mf.meshloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface MeshLoader {
	
	public Mesh load(String fileName) throws IOException;
	
	public Mesh load(File file) throws IOException;
	
	public Mesh load(InputStream stream) throws IOException;
}
