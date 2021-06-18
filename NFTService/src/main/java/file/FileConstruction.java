package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
//class for construction of file
public class FileConstruction {

	public ByteArrayResource constructFile(MultipartFile file) throws IOException {
		System.out.println("Sono prima della costruzione");
		ByteArrayResource contentsAsResource = new ByteArrayResource(file.getBytes()) {
	        @Override
	        public String getFilename() {
	          return file.getOriginalFilename(); // Filename has to be returned in order to be able to post.
	        }
	      };
	      System.out.println("Invio il file costruito al mint");
	      System.out.println(contentsAsResource.getFilename());
	      System.out.println(contentsAsResource.getByteArray());
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
