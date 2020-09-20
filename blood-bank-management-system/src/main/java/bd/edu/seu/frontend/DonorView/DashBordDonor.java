package bd.edu.seu.frontend.DonorView;

import bd.edu.seu.frontend.UserView.UserMainView;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


//"A(+ve)","A(-ve)","B(+ve)","B(-ve)","O(+ve)","O(-ve)","AB(+ve)","AB(-ve)"
// Use this bloods group in combobox for define donor blood group in Donor registration


@Route(value = "dash-bord-donor",layout = DonorMainView.class)
@PageTitle("JBMK | Dash Bord(Donor)")
public class DashBordDonor extends VerticalLayout {

    MenuBar menuBar = new MenuBar();
    TextArea textArea = new TextArea();
    MenuItem aboutus, service;
    String defult = "More then 200 Monthly Active Donor Donate Blood  in Our service\n"+
            "Donor Satisfaction is Highest priority\n"+
            "---------------Your Donation can Save some one Life------------------";

    String aboutinfo = "Model, Design, Development \n" +
            "MD. Nahidul Islam(MK) & MD. Joniyed Bhuiyan(JB)\n" +
            "JAVA web developers(Back End Specialist), Big Data Analysers\n"+
            "Computer Science & Engineering\n" +
            "SouthEast University\n" +
            "2017000000088@seu.edu.bd\n" +
            "2017000000092@seu.edu.bd\n";
    String serviceinfo = "\uF076\tWe provide Best quality of Blood in very Less Amount in every city in Bangladesh\n" +
            "\uF076\tWe maintain our reputation through our incomparable Service\n" +
            "\uF076\tWe ensure the 100%  best quality of Blood\n" +
            "\uF076\tWe deliver the blood in any rural area within 2 days\n" +
            "\uF076\tWe consider the financial Condition of a customer\n" +
            "\uF076\tWe try to response in Every Request of our Customer with is One minute\n" +
            "\uF076\tWe always active for our customer Service\n";

    public DashBordDonor()
    {
        addClassName("list");
        setAlignItems(Alignment.CENTER);
        menuBarConfig();



        add(new H1("Welcome To JBMK Blood Bank Management System"),menuBar,textArea);

    }


    private void menuBarConfig() {
        textArea.setHeightFull();
        textArea.setWidth("500px");
        textArea.setReadOnly(true);
        textArea.setValue(defult);
        aboutus = menuBar.addItem("About us", e-> textArea.setValue(aboutinfo));
        service = menuBar.addItem("Services", e-> textArea.setValue(serviceinfo));

    }

}
