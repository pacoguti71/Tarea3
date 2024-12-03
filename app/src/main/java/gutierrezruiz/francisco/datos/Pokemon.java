package gutierrezruiz.francisco.datos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pokemon {
    @SerializedName("name")
    private String nombre;

    @SerializedName("id")
    private int indice;

    @SerializedName("sprites")
    private Sprite foto; // Objeto que contiene las URL de las fotos

    @SerializedName("types")
    private List<Tipo> tipos; // Lista para los tipos

    @SerializedName("weight")
    private int peso;

    @SerializedName("height")
    private int altura;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Sprite getFoto() {
        return foto;
    }

    public void setFoto(Sprite foto) {
        this.foto = foto;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    // Clases auxiliares para estructuras complejas en JSON
    public static class Sprite {
        @SerializedName("front_default")
        private String frontDefault;

        public String getFrontDefault() {
            return frontDefault;
        }

        public void setFrontDefault(String frontDefault) {
            this.frontDefault = frontDefault;
        }
    }

    public static class Tipo {
        @SerializedName("type")
        private TipoDetalle type;

        public TipoDetalle getType() {
            return type;
        }

        public void setType(TipoDetalle type) {
            this.type = type;
        }

        public static class TipoDetalle {
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
