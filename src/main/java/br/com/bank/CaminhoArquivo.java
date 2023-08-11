package br.com.bank;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {

    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        Integer directoryNumber = getDirectoryNumber((double) id);

        String directory = "/tmp/" + directoryNumber + "/";

        String file = directory + id;

        return new CaminhoArquivo(Paths.get(directory), Paths.get(file));
    }

    private static Integer getDirectoryNumber(Double id) {
        return (int) Math.ceil(id / 1000);
    }

}
