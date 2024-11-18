import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Vector;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

public class ArchiveExtractor {
	String fname;
	private static Vector<String> files = new Vector<>(1, 1);
	ArchiveExtractor(String fnamee)
	{
		fname = fnamee;
	}
	private void getFileList()
	{
        RandomAccessFile randomAccessFile = null;
        IInArchive inArchive = null;
        try {
            randomAccessFile = new RandomAccessFile(fname, "r");
            inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile));
            ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();
            for (ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {	
                if (!item.isFolder()) {
                    ExtractOperationResult result;
                    result = item.extractSlow(new ISequentialOutStream() {
                        public int write(byte[] data) throws SevenZipException {
                        	try (FileOutputStream fos = new FileOutputStream(item.getPath())) {
                        		   try {
									fos.write(data);
								} catch (IOException e) {
									e.printStackTrace();
								}
                        		} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
                            return data.length;
                        }
                    });

                    if (result == ExtractOperationResult.OK) {
                        files.add(item.getPath());
                     
                    } else {
                        System.err.println("Error extracting item: " + result);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurs: " + e);
        } finally {
            if (inArchive != null) {
                try {
                    inArchive.close();
                } catch (SevenZipException e) {
                    System.err.println("Error closing archive: " + e);
                }
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e);
                }
            }
        }
	}
	
	public String select()
	{
		getFileList();
		if(files.capacity() > 1)
		{
		System.out.println("Выберите файл");
		System.out.println(files.capacity());
        for (int i = 0; i < files.capacity(); i++)
        {
        	System.out.println(i + ": " + files.get(i));
        }	
        Scanner in = new Scanner(System.in);
        int selected = in.nextInt();
        String Fname = files.get(selected);
        System.out.println(Fname);
        DeleteUnnesessary(selected);
        return Fname;
		}
		return files.get(0);
	}
	
	private void DeleteUnnesessary(int toKeep)
	{
        for (int i = 0; i < files.capacity(); i++)
        {
        	if (i != toKeep)
        	{
        		File toDelete = new File("unzipped_" + files.get(i));
        		toDelete.delete();
        	}
        }
	}
}
