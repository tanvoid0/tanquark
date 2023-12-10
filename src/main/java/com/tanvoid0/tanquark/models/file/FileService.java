package com.tanvoid0.tanquark.models.file;

import jakarta.inject.Singleton;
import jakarta.ws.rs.core.MultivaluedMap;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Map;

@Singleton
@Slf4j
public class FileService {

    public FileItemVO uploadFile(MultipartFormDataInput input, final String serverUrl) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        // InputParts is a list of files. We can handle batch upload later. for now we'll focus on uploading a single file.
        if (inputParts.isEmpty()) {
            return null;
        }
        return writeFile(inputParts.get(0), serverUrl);
    }

    public byte[] readFile(final String fileType, final String fileName) throws NoSuchFileException {
        try {
            return FileItemVO.read(fileType, fileName);
        } catch (NoSuchFileException ex) {
            log.debug("File doesn't exist");
            throw ex;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public FileItemVO writeFile(final InputPart inputPart, final String serverUrl) {
        try {
            MultivaluedMap<String, String> header =
                    inputPart.getHeaders();
            final String fileName = getFileName(header);
            final String fileType = getFileType(header);
            final InputStream inputStream = inputPart.getBody(InputStream.class, null);
            final FileItemVO file = new FileItemVO(inputStream, fileName, fileType, serverUrl);
            file.write();
            return file;
        } catch (FileAlreadyExistsException ex) {
            log.debug("File already exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.
                getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "";
    }


    private String getFileType(MultivaluedMap<String, String> header) {
        return header.getFirst("Content-Type");
    }
//
//    @ConsumeEvent(blocking = true, value = "file-service")
//    public void processFile(BufferedReader br) throws InterruptedException {
//        log.info("processFile() begin");
//
//        try (br) {
//            String currentLine = null;
//            while ((currentLine = br.readLine()) != null) {
//                log.info("currentLine " + currentLine);
//            }
//        } catch (IOException ex) {
//            log.error("Error", ex);
//        }
//
//        log.info("processFile() end");
//    }
}
