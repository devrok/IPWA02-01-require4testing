package controllers;


import java.io.Serializable;

import daos.DatabaseInitializer;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import session.CurrentUser;
import utilities.LoginValidator;

@Named
@ViewScoped
public class LoginController implements Serializable {
	
	@Inject
	private DatabaseInitializer dbi;
	
	@Inject
	private LoginValidator loginValidator;
	
	@Inject 
	private CurrentUser currentUser;
	
	private String username;
	private String password;
	private String tempUsername;
	
	
	public LoginController() {
		
	}

	@PostConstruct
	public void init() {
		// Rollen anlegen
		dbi.initRollen();
		
		// Benutzer anlegen
		dbi.initBenutzer();
		
		// Anforderungen anlegen
		dbi.initAnforderungen();
	
		// Testfälle anlegen
		dbi.initTestfaelle();
		
		// Testläufe anlegen
		dbi.initTestlaeufe();
		
		// Testdurchläufe anlegen
		dbi.initTestlaufDurchfuehrungen();
		
		// Testfalldurchfläufe ergänzen
		dbi.initTestfallDurchfuehrungen();
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public void postValidateUser(ComponentSystemEvent ev) {
		UIInput temp = (UIInput) ev.getComponent();
		this.tempUsername = (String) temp.getValue();
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password = (String) value;
		loginValidator.validateLogin(currentUser, tempUsername, password);
		if (!currentUser.isValid()) {
			throw new ValidatorException(new FacesMessage("Login falsch!"));
		}
	}
	
	public String login() {
		resetInput();
		if (currentUser.isValid()) {
			return "main";
		} else {
			return "login";
		}
	}
	
	public void cancel() {
		resetInput();
	}
	
	private void resetInput() {
		this.setPassword(null);
		this.setUsername(null);
	}
}