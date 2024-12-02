package gutierrezruiz.francisco.datos;

import com.google.gson.annotations.SerializedName;

public class Pokemon {
    @SerializedName("name")
    private String nombre;

    @SerializedName("url")
    private String url;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

