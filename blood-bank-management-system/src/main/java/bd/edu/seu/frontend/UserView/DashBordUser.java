package bd.edu.seu.frontend.UserView;

import bd.edu.seu.Backend.Model.Donor;
import bd.edu.seu.Backend.Service.DonorService;
import bd.edu.seu.frontend.DonorView.DonorMainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Level;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.stream.Stream;


@Route(value = "userDashBoard",layout = UserMainView.class)
@PageTitle("JBMK | Dash Bord(user)")
public class DashBordUser extends VerticalLayout {

    MenuBar menuBar = new MenuBar();
    TextArea textArea = new TextArea();
    MenuItem aboutus, service, price, payment, bloodAvailableCheck;
    String defult = "More then 200 Daily Active user Use Our service\n"+
            "User Satisfaction is Highest priority ";

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

    String priceinfo = "\uF076\tA(+ve) ---------->550/-\n" +
            "\uF076\tA(-ve) ----------> 650/-\n" +
            "\uF076\tB(+ve) ----------> 500/-\n" +
            "\uF076\tB(-ve) ----------> 800/-\n" +
            "\uF076\tO(+ve)---------->400/-\n" +
            "\uF076\tO(-ve) ---------->600/-\n" +
            "\uF076\tAB(+ve) ---------->700/-\n" +
            "\uF076\tAB(-ve) ---------->1000/-\n" +
            "The price May change over the time\n";
    String paymentinfo = "\n" +
            "\uF0D8\tThere are Three way to Make payment in our System\n" +
            "\t\t>bKash( 01624756285)\n" +
            "\t\t>Rocket( 01626862889)\n" +
            "\t\t>Cash On Deliver\n" +
            "\uF0D8\tAfter Accepting Request You have put your transaction Code on your Approval Request\n" +
            "\uF0D8\tBefore Approval Don’t Send any Sort of Money as a Payment\n";

    private final DonorService donorService;
    public DashBordUser(DonorService donorService)
    {
        this.donorService = donorService;
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
        price = menuBar.addItem("Price", e-> textArea.setValue(priceinfo));
        payment = menuBar.addItem("Payment",e-> textArea.setValue(paymentinfo));
        bloodAvailableCheck = menuBar.addItem("Blood Available Check",e->textArea.setValue(check()));
    }

    private String check() {
        String value = "";
        int A_pos,A_neg,B_pos,B_neg,O_pos,O_neg,AB_pos,AB_neg;
        //"A(+ve)","A(-ve)","B(+ve)","B(-ve)","O(+ve)","O(-ve)","AB(+ve)","AB(-ve)"
        A_pos = donorService.findByBloodGroup("A(+ve)").size();
        A_neg = donorService.findByBloodGroup("A(-ve)").size();
        B_pos = donorService.findByBloodGroup("B(+ve)").size();
        B_neg = donorService.findByBloodGroup("B(-ve)").size();
        O_pos = donorService.findByBloodGroup("O(+ve)").size();
        O_neg = donorService.findByBloodGroup("O(-ve)").size();
        AB_pos = donorService.findByBloodGroup("AB(+ve)").size();
        AB_neg = donorService.findByBloodGroup("AB(+ve)").size();

        value = "A(+ve) =  "+A_pos+"  Donors \n"+"A(-ve) =  "+A_neg+"  Donors \n"+"B(+ve) = "+B_pos+"  Donors \n"+"B(-ve) = "+B_neg+"  Donors \n"+
                "O(+ve) = "+O_pos+"  Donors \n"+"O(-ve) = "+O_neg+"  Donors \n"+"AB(+ve) = "+AB_pos+"  Donors \n"+"AB(-ve) = "+AB_neg+"  Donors \n";
        return value;
    }


}
