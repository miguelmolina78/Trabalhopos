package br.com.facef.aula32.exception;

public class Response {

    private String developerMessage;

    public Response(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
