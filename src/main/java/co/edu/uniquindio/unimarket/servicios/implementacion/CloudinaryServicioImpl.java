package co.edu.uniquindio.unimarket.servicios.implementacion;

import co.edu.uniquindio.unimarket.servicios.interfaces.CloudinaryServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicioImpl implements CloudinaryServicio {

    private Cloudinary cloudinary;
    private Map<String, String> config;

    public CloudinaryServicioImpl(){
        config = new HashMap<>();
        config.put("cloud_name", "dsr8wu5yn");
        config.put("api_key", "115673969154647");
        config.put("api_secret", "xq5GjIsdpxCn8yWehNlGXje_-LU");
        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(File file, String carpeta) throws Exception{
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", String.format("co/edu/uniquindio/unimarket/%s", carpeta)));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }

    @Override
    public File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
