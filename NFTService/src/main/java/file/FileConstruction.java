package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
//class for construction of file
public class FileConstruction {

	public ByteArrayResource ConstructFile(MultipartFile file) throws IOException {
		ByteArrayResource contentsAsResource = new ByteArrayResource(file.getBytes()) {
	        @Override
	        public String getFilename() {
	          return file.getOriginalFilename(); // Filename has to be returned in order to be able to post.
	        }
	      };
	      return contentsAsResource;
	}
	public String saveFile(MultipartFile file, String path, String id) throws Exception {
		id = id + this.getFileType(file.getOriginalFilename());
		Files.copy(file.getInputStream(), Paths.get(path).resolve(id));
		return id;
	}
	private String getFileType(String fileName) {
		fileName = fileName.substring(fileName.indexOf('.'));
		return fileName;
	}
}
