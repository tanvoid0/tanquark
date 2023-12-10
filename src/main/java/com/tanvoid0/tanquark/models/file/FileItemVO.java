package com.tanvoid0.tanquark.models.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tanvoid0.tanquark.common.base.file.EFileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FileItemVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1989648177480901354L;

    private String fileName;

    @JsonIgnore
    private byte[] content;

    @JsonIgnore
    private File file;
    private String filePath;
    private String webUrl;
    private EFileType type;
    private String contentType;

    public FileItemVO(final InputStream inputStream, final String fileName, final String contentType, final String serverUrl) throws IOException, URISyntaxException {
        this.content = IOUtils.toByteArray(inputStream);
        this.fileName = generateUniqueFileName(fileName.replaceAll(" ", "-"));
        this.contentType = contentType;
        if (contentType.startsWith("image")) {
            this.type = EFileType.IMAGE;
        } else if (contentType.startsWith("video")) {
            this.type = EFileType.VIDEO;
        } else {
            this.type = EFileType.OTHER;
        }

        this.webUrl = serverUrl + "/file/read/" + this.type.name().toLowerCase() + "/" + this.fileName;
        this.filePath = getStorageDirectory().getAbsolutePath() + File.separator + this.type.name().toLowerCase() + File.separator;
        if (!Files.exists(Paths.get(filePath))) {
            Files.createDirectories(Paths.get(filePath));
        }
        this.filePath += this.fileName;
    }

    public static String generateUniqueFileName(final String fileName) {
        return UUID.randomUUID().toString().substring(0, 6) + "-" + fileName;
    }

    private static File getStorageDirectory() throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resourceURL = classLoader.getResource("storage");
        assert resourceURL != null;
        URI resourceURI = resourceURL.toURI();
        Path resourcePath = Paths.get(resourceURI);
        return new File(resourcePath.toString());
    }

    public static byte[] read(final String fileType, final String fileName) throws IOException, URISyntaxException {
        final String filePath = FileItemVO.getStorageDirectory().getAbsolutePath() + File.separator + fileType + File.separator + fileName;
        Path imageFile = Paths.get(filePath);
        return Files.readAllBytes(imageFile);
    }

    public void write() throws IOException {
        Files.write(Paths.get(filePath), content, StandardOpenOption.CREATE_NEW);
    }
}
