package pe.edu.idat.ms_usuarios.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank(message = "Los nombres son obligatorios.")
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres.")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios.")
    @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres.")
    private String apellidos;

    @NotBlank(message = "El tipo de documento es obligatorio.")
    @Size(max = 20, message = "El tipo de documento no puede superar los 20 caracteres.")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio.")
    @Size(max = 20, message = "El número de documento no puede superar los 20 caracteres.")
    private String numeroDocumento;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Correo electrónico inválido.")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres.")
    private String correo;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres.")
    private String telefono;

    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres.")
    private String direccion;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String nombres,
                          String apellidos,
                          String tipoDocumento,
                          String numeroDocumento,
                          String correo,
                          String telefono,
                          String direccion) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}