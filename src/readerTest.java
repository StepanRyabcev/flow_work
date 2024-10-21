import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class readerTest {

	private static Path tempFile;
	private static Path tempXMLFile;
	private static Path tempZipFile;
	private static Path tempFileEncr;
	private static Path tempZipEncr;
	private static enpryptionOptions op;
	private static enpryptionOptions op1;
	
    @BeforeAll
    public static void setUp() throws Exception {
    	op = new enpryptionOptions();
    	op1 = new enpryptionOptions();
    	op1.SetDecr(true);
    	op1.SetDecrKey("key");
    	
        tempFile = Files.createTempFile("testFileTXT", ".txt");
        Files.writeString(tempFile, "Hello, World!");
        tempXMLFile = Files.createTempFile("testFileXML", ".xml");
        String content = """
        		<?xml version="1.0" encoding="UTF-8"?>
        		<expression>	
        			<T>a + a + c</T>
        			<Variable name="a">5</Variable>
        			<Variable name="b">6</Variable>
        			<Variable name="c">8</Variable>
        		</expression>
        		""";
        Files.writeString(tempXMLFile, content);
        
        tempZipFile = Files.createTempFile("testFileZIP", ".zip");
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(tempZipFile))) {	
            ZipEntry entry = new ZipEntry("test.txt");
            zos.putNextEntry(entry);
            zos.write("Hello, World!".getBytes());
            zos.closeEntry();
        }
        
        tempFileEncr = Files.createTempFile("testFileEncr", ".txt");
        String encrypted = crypto.encrypt("Hello, World!", "key");
        Files.writeString(tempFileEncr, encrypted);
        
        tempZipEncr = Files.createTempFile("testFileZIPEncr", ".zip");
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(tempZipEncr))) {	
            ZipEntry entry = new ZipEntry("test.txt");
            zos.putNextEntry(entry);
            zos.write(encrypted.getBytes());
            zos.closeEntry();
        }
    }

    @AfterAll
    public static void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(tempXMLFile);
        Files.deleteIfExists(tempZipFile);
        Files.deleteIfExists(tempFileEncr);
        Files.deleteIfExists(tempZipEncr);
    }	
	
	@Test
	void testTXT() {
		reader rd = new reader(tempFile.toString(), op);
		String out = rd.read();
		assertEquals("Hello, World!", out);
	}
		
	@Test
	void testXML()
	{
		reader rd = new reader(tempXMLFile.toString(), op);
		String out = rd.read();
		String sholdbe = "a + a + c.\na = 5.\nb = 6.\nc = 8";
		assertEquals(sholdbe, out);
	}
		
	@Test
	void testZIP()
	{
		reader rd = new reader(tempZipFile.toString(), op);
		String out = rd.read();
		assertEquals("Hello, World!", out);
	}
	
	@Test
	void testDecryption()
	{
		reader rd = new reader(tempFileEncr.toString(), op1);
		String out = rd.read();
		assertEquals("Hello, World!", out);
	}
	
	@Test
	void testDecryptionFromZip()
	{
		reader rd = new reader(tempZipEncr.toString(), op1);
		String out = rd.read();
		assertEquals("Hello, World!", out);
	}
}
