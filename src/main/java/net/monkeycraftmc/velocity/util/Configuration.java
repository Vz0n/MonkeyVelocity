package net.monkeycraftmc.velocity.util;

import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Configuration {

    private final File file;
    private Toml toml;

    public Configuration(File folder, String filename){
        this.file = new File(folder, filename);
    }

    public boolean exists(){
        return this.file.exists();
    }

    public void saveFile(){
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("config.toml");
            Files.copy(stream, file.toPath());
        }catch(IOException | IllegalStateException e){
          e.printStackTrace();
        }
    }

    public void loadFile(){
        toml = new Toml().read(this.file);
    }

    public Toml getValues(){
        return toml;
    }

}
