package bd.edu.seu.frontend.AdminView;

import bd.edu.seu.Backend.Model.BloodGroup;
import bd.edu.seu.Backend.Model.Donor;
import bd.edu.seu.Backend.Model.User;
import bd.edu.seu.Backend.Service.DonorService;
import bd.edu.seu.Backend.Service.UserService;
import com.vaadin.flow.component.charts.model.Level;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;




@Route(value = "donor-grid",layout = AdminMainView.class)
@PageTitle("JBMK | UserGrid(Admin)")
public class DonorGrid extends VerticalLayout {
    ComboBox<String> bloodGroupComboBox = new ComboBox<>();
    Grid<Donor> donorGrid = new Grid<>(Donor.class);
    TextField textField = new TextField();
    int count;
    private DonorService donorService;
    public DonorGrid(DonorService donorService)
    {
        this.donorService = donorService;
        addClassName("list");
        setSizeFull();
        configureGrid();
        add(donorGrid);
        Div div = new Div(donorGrid);
        div.setSizeFull();
        div.addClassName("Content");
        add(ToolBar(),div);
        updateGrid();
    }

    private void configureGrid() {
        donorGrid.addClassName("Grid");
        donorGrid.setSizeFull();
        donorGrid.setColumns("name","address","phone","email","account","bloodGroup");
        donorGrid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    public void updateGrid() {

        String value1 = bloodGroupComboBox.getValue();
        if(value1==null)
        {
            List<Donor> donors = donorService.FindAll();
            count = donors.size();
            donorGrid.setItems(donors);
        }else{
            List<Donor> donors = donorService.findByBloodGroup(value1);
            count = donors.size();
            donorGrid.setItems(donors);
        }
        textField.setValue("Total Donor = "+ count );
    }
    private HorizontalLayout ToolBar() {

        bloodGroupComboBox.setPlaceholder("Filter by Blood Group...");
        bloodGroupComboBox.setItems("A(+ve)","A(-ve)","B(+ve)","B(-ve)","O(+ve)","O(-ve)","AB(+ve)","AB(-ve)");
        bloodGroupComboBox.setClearButtonVisible(true);
        bloodGroupComboBox.addValueChangeListener(e-> updateGrid());
        HorizontalLayout layout = new HorizontalLayout(bloodGroupComboBox,textField);
        layout.addClassName("Toolbar");
        return layout;
    }
}
