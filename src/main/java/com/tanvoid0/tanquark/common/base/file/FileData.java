package com.tanvoid0.tanquark.common.base.file;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FileData extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2688512321101324276L;

    protected String name;

    @Enumerated(EnumType.STRING)
    protected EFileType type;

    protected String url;

    protected String hostUrl;
}
