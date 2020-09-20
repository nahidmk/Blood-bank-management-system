package bd.edu.seu.frontend.AdminView;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import javax.servlet.http.HttpSession;

@CssImport("./styles/shared-styles.css")
public class AdminMainView extends AppLayout {

    public AdminMainView(HttpSession httpSession) {
         createHeader(httpSession);
         createDrawer();
    }

    private void createDrawer() {
        RouterLink routerLink = new RouterLink("User", UserGrid.class);
        routerLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(
                new VerticalLayout(
                        routerLink,
                        new RouterLink("Donor",DonorGrid.class),
                        new RouterLink("Notice",NoticeGrid.class),
                        new RouterLink("DashBoard", DashBordAdmin.class),
                        new RouterLink("Account",AccountGrid.class)
                )
        );
    }

    private void createHeader(HttpSession httpSession) {
        H1 logo = new H1("JBMK Blood Bank Management System(Admin penal)");
        logo.addClassName("logo");
        Anchor anchor = new Anchor(logout(httpSession),"Log Out");
        HorizontalLayout horizontalLayout = new HorizontalLayout(new DrawerToggle(),logo,anchor);
        horizontalLayout.expand(logo);
        horizontalLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        horizontalLayout.setWidth("100%");
        horizontalLayout.addClassName("header");
        addToNavbar(horizontalLayout);
    }

    private String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "login";
    }
}
