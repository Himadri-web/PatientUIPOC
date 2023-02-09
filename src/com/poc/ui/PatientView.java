package com.poc.ui;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.poc.RestClient;
import com.poc.model.Address;
import com.poc.model.Patient;
import com.poc.model.Telephone;
import com.poc.util.PopupWindow;

public class PatientView {
	public Shell shell;
	public Table table;
	public Display displayParent;

	public List<Patient> patientList = new ArrayList<>();

	private static final String[] SEARCH_CRITERIA = { "Patient ID", "Patient Name" };
	protected static final String String = null;

	private PatientEntry patientEntry;

	public PatientEntry getPatientEntry() {
		return patientEntry;
	}

	public void setPatientEntry(PatientEntry patientEntry) {
		this.patientEntry = patientEntry;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public PatientView(Display displayParent) {
		super();
		this.displayParent = displayParent;
		System.out.println("displayParent : " + displayParent);
	}

	public void showPatientDetails(boolean isAllPatientList) {
		shell = new Shell(displayParent);
		showPatientAfterCreation(isAllPatientList);
		shell.open();
		shell.layout();
		shell.setSize(800, 700);
		
		while (!shell.isDisposed()) {
			if (displayParent.readAndDispatch()) {
				displayParent.sleep();
			}
		}

	}

	private void showPatientAfterCreation(boolean isAllPatientList) {

		try {
			if (isAllPatientList) {
				List<Patient> patientList = RestClient.fetchPatientList();
				this.setPatientList(patientList);
			}
			createPageView(displayParent, isAllPatientList);

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createPageView(Display displayParent, boolean isAllPatientList) {
		Label label = new Label(shell, SWT.CENTER);
		Label searchlabel = new Label(shell, SWT.CENTER);

		Text searchText = new Text(shell, SWT.CENTER);

		Button searchButton = new Button(shell, SWT.NONE);
		searchButton.setBounds(600, 100, 150, 30);
		searchButton.setText("Search");
		searchButton.setEnabled(true);
		Color colorSearch = new Color(displayParent, 10, 150, 150);
		searchButton.setBackground(colorSearch);

		Button viewButton = new Button(shell, SWT.NONE);
		Button editButton = new Button(shell, SWT.NONE);
		Button deleteButton = new Button(shell, SWT.NONE);

		label.setText("Cerner Patient's Page");
		label.setBounds(300, 10, 1500, 50);
		label.setFont(new Font(displayParent, "boldfont", 20, SWT.BOLD));
		label.pack();

		searchlabel.setText("Patient Search By");
		searchlabel.setBounds(30, 70, 1000, 30);
		searchlabel.setFont(new Font(displayParent, "boldfont", 15, SWT.BOLD));
		searchlabel.pack();

		searchText.setBounds(180, 100, 380, 30);

		viewButton.setBounds(400, 500, 150, 30);
		viewButton.setText("View");
		viewButton.setEnabled(false);

		editButton.setBounds(600, 500, 150, 30);
		editButton.setText("Edit");
		editButton.setEnabled(false);

		deleteButton.setBounds(800, 500, 150, 30);
		deleteButton.setText("Delete");
		deleteButton.setEnabled(false);

		deleteButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Delete Paitent");
				// TableItem[] item = table.getSelection();
				Integer index = table.getSelectionIndex();
				System.out.println("Selected record index value is: " + index);
				TableItem selectedRecord = table.getItem(index);
				Integer patientId = Integer.valueOf(selectedRecord.getText(0));
				System.out.println("Selected record'spatient id : " + patientId);
				try {
					HttpResponse<String> response = RestClient.removePatient(patientId);
					MessageBox messageBox = null;
					if (response.statusCode() == 200) {
						// messageBox = new MessageBox(shell, SWT.ICON_WORKING);
						// messageBox.setText("Success");
						// messageBox.setMessage("Patient ID " + patientId +" deleted successfully");
						// messageBox.setButtonLabels();

						Integer option = PopupWindow.showDialogeBox(shell, SWT.ICON_WORKING, "Success",
								"Patient ID " + patientId + " deleted successfully");

						patientEntry.setDataForDefaultPage();
						shell.close();
					} else {
						PopupWindow.showDialogeBox(shell,SWT.ICON_ERROR, "Fail", response.body());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		Combo comboSearchCriteria = new Combo(shell, SWT.DROP_DOWN);
		comboSearchCriteria.setItems(SEARCH_CRITERIA);
		comboSearchCriteria.setBounds(30, 100, 120, 30);

		searchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside Search button listener");
				System.out.println("Selected search field : " + comboSearchCriteria.getText());
				Boolean isRemoveAllDataFromTable = table != null && table.getItemCount() > 0 ? true : false;

				if (comboSearchCriteria.getText().trim().isBlank()) {
					PopupWindow.showDialogeBox(shell, SWT.ICON_WARNING, "Warning",
							"Please select the search criteria!");
				}

				if (comboSearchCriteria.getText().equals("Patient ID")) {
					System.out.println("Search by patient ID");
					String patientId = searchText.getText();
					try {
						Patient patient = RestClient.fetchPatientById(Integer.valueOf(patientId));
						List<Patient> patientList = new ArrayList<>();
						patientList.add(patient);
						createPatientsTable(displayParent, searchlabel, viewButton, editButton, deleteButton,
								patientList, isRemoveAllDataFromTable);
						System.out.println("Search by patient ID completed");

					} catch (NumberFormatException | IOException | InterruptedException e) {
						Integer option = showDialogeBox(SWT.ICON_ERROR, "Fail",
								"Patient does not exist with Patient ID :  " + patientId);
						
						e.printStackTrace();
					}

				} else if (comboSearchCriteria.getText().equals("Patient Name")) {
					System.out.println("Search by patient Name");
					String patientName = searchText.getText();
					try {
						// Patient patient = RestClient.fetchPatientById(Integer.valueOf(patientName));
						Patient patient = RestClient.fetchPatientById(Integer.valueOf(10));
						List<Patient> patientList = new ArrayList<>();
						patientList.add(patient);
						createPatientsTable(displayParent, searchlabel, viewButton, editButton, deleteButton,
								patientList, isRemoveAllDataFromTable);
						System.out.println("Search by patient Name completed");

					} catch (NumberFormatException | IOException | InterruptedException e) {
						e.printStackTrace();
					}

				}

				//PopupWindow.showDialogeBox(shell, SWT.ICON_INFORMATION, "In-Progress",
						//"Search functionality is under construction");
				System.out.println("End Search button listener");

			}
		});

		viewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside view listener");
				Integer index = table.getSelectionIndex();
				System.out.println("Selected record index value is: " + index);
				TableItem selectedRecord = table.getItem(index);
				Integer patientId = Integer.valueOf(selectedRecord.getText(0));
				System.out.println("Selected record'spatient id : " + patientId);
				try {
					Patient patient = RestClient.fetchPatientById(Integer.valueOf(patientId));
					patientEntry.setDataForViewAndModify(patient, displayParent, true);
					shell.close();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
					PopupWindow.showDialogeBox(shell,SWT.ICON_ERROR, "Fail", "Patient deatils cannot view for Patient ID : " + patientId);
				}

			}
		});

		editButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside Edit listener");
				Integer index = table.getSelectionIndex();
				System.out.println("Selected record index value is: " + index);
				TableItem selectedRecord = table.getItem(index);
				Integer patientId = Integer.valueOf(selectedRecord.getText(0));
				System.out.println("Selected record'spatient id : " + patientId);
				try {
					Patient patient = RestClient.fetchPatientById(Integer.valueOf(patientId));
					patientEntry.setDataForViewAndModify(patient, displayParent, false);
					shell.close();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					PopupWindow.showDialogeBox(shell,SWT.ICON_ERROR, "Fail", "Patient deatils cannot edit for Patient ID : " + patientId);
				}

			}
		});

		Boolean isRemoveAllDataFromTable = table != null && table.getItemCount() > 0 ? true : false;

		if (isAllPatientList) {
			createPatientsTable(displayParent, label, viewButton, editButton, deleteButton, getPatientList(),
					isRemoveAllDataFromTable);
		}

	}

	private Integer showDialogeBox(int iconWorking, String text, String message) {
		MessageBox messageBox;
		messageBox = new MessageBox(shell, iconWorking);
		messageBox.setText(text);
		messageBox.setMessage(message);
		return messageBox.open();
	}

	private void createPatientsTable(Display displayParent, Label label, Button viewButton, Button editButton,
			Button deleteButton, List<Patient> patientList, boolean isDataRemoveFromTable) {

		if (isDataRemoveFromTable) {
			System.out.println("Remove all table data");
			table.clearAll();
			table.removeAll();
			TableColumn[] columns = table.getColumns();
			for (int index = 0; index < columns.length; index++) {
				columns[index].dispose();
			}

			TableItem[] items = table.getItems();
			for (int index = 0; index < items.length; index++) {
				items[index].dispose();
			}
		} else {
			table = new Table(shell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);// | SWT.ARROW_UP| SWT.CURSOR_ARROW);
		}

		// String[] tableHeaders = {"patient_id", "patient_name", "gender",
		// "date_of_birth",
		// "address_type", "street", "city", "state", "postal_code",
		// "phone_type", "country_code", "phone_number"};

		String[] tableHeaders = { "patient_id", "patient_name", "gender", "date_of_birth", "Present address_type",
				"Present street", "Present city", "Present state", "Present postal_code", "Permanent address_type",
				"Permanent street", "Permanent city", "Permanent state", "Permanent postal_code", "Primary phone_type",
				"Primary country_code", "Primary phone_number", "Alt phone_type", "Alt country_code",
				"Alt phone_number" };
		for (int index = 0; index < tableHeaders.length; index++) {
			TableColumn column = new TableColumn(table, SWT.BOLD);
			column.setWidth(50);
			column.setText(tableHeaders[index]);
			table.getColumn(index).pack();
		}
		table.setBounds(80, 200, 600, 200);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// GridData data = new GridData(SWT.LEFT, SWT.TOP, true, true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);

		if (!patientList.isEmpty()) {
			for (Patient patient : patientList) {
				TableItem item = new TableItem(table, SWT.ITALIC);
				item.setText(0, patient.getPatientId().toString());
				item.setText(1, patient.getPatientName());
				item.setText(2, patient.getGender().toString());
				item.setText(3, patient.getDateOfBirth().toString());

				// item.setText(4, patient.getAddress().get(0).getAddressType());
				// item.setText(5, patient.getAddress().get(0).getStreet());
				// item.setText(6, patient.getAddress().get(0).getCity());
				// item.setText(7, patient.getAddress().get(0).getState());
				// item.setText(8, patient.getAddress().get(0).getPostalCode());

				for (Address address : patient.getAddress()) {
					if (address.getAddressType().equalsIgnoreCase("Present Address")) {
						item.setText(4, address.getAddressType());
						item.setText(5, address.getStreet());
						item.setText(6, address.getCity());
						item.setText(7, address.getState());
						item.setText(8, address.getPostalCode());

					} else if (address.getAddressType().equalsIgnoreCase("Permanent Address")) {
						item.setText(9, address.getAddressType());
						item.setText(10, address.getStreet());
						item.setText(11, address.getCity());
						item.setText(12, address.getState());
						item.setText(13, address.getPostalCode());
					}
				}

				// item.setText(9, patient.getMobileNumber().get(0).getPhoneType());
				// item.setText(10, patient.getMobileNumber().get(0).getCountryCode());
				// item.setText(11, patient.getMobileNumber().get(0).getPhoneNumber());
				for (Telephone telephone : patient.getMobileNumber()) {
					if (telephone.getPhoneType().equalsIgnoreCase("Primary Contact")) {
						item.setText(14, telephone.getPhoneType());
						item.setText(15, telephone.getCountryCode());
						item.setText(16, telephone.getPhoneNumber());

					} else if (telephone.getPhoneType().equalsIgnoreCase("Alternate Contact")) {
						item.setText(17, telephone.getPhoneType());
						item.setText(18, telephone.getCountryCode());
						item.setText(19, telephone.getPhoneNumber());
					}
				}

			}

		}

		System.out.println("No. of records found in the table: " + table.getItems().length);

		table.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				TableItem[] selection = table.getSelection();

				if (selection.length == 1) {
					System.out.println("Inside else selecttion");
					viewButton.setEnabled(true);
					editButton.setEnabled(true);
					deleteButton.setEnabled(true);
				} else {
					// System.out.println("You should not select more than one patient ata time");
					viewButton.setEnabled(false);
					editButton.setEnabled(false);
					deleteButton.setEnabled(false);
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
					messageBox.setText("Error");
					messageBox.setMessage("You should not select morethan one patient");
					Integer selectedValue = messageBox.open();

					switch (selectedValue) {
					case SWT.YES:

					case SWT.NO:
						break;
					case SWT.CANCEL:

						System.out.println("Selected Option Value : " + selectedValue);

					}
				}
			}
		});

		table.pack();
		
		System.out.println("End");

	}

}
