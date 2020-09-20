package bd.edu.seu.frontend.AdminView;

import bd.edu.seu.Backend.Model.Notice;
import bd.edu.seu.Backend.Service.NoticeService;
import bd.edu.seu.frontend.DonorView.DonorMainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Route(value = "notice-grid",layout = AdminMainView.class)
@PageTitle("JBMK | Notice(Admin)")
public class NoticeGrid extends VerticalLayout {

    DatePicker datePicker = new DatePicker();
    TextArea textArea = new TextArea("Notice");
    Button save = new Button("Send");
    Button delete = new Button("Delete");
    LocalDate localDate1;
    String note;
    Grid<Notice> noticeGrid = new Grid<>(Notice.class);
    private NoticeService noticeService;
    public NoticeGrid(NoticeService noticeService)
    {
        this.noticeService = noticeService;
        addClassName("list");
        setSizeFull();
        configureGrid();
        add(ToolBar(),noticeGrid);
        updateGrid();
//        noticeGrid.addItemClickListener(
//                event -> {
//                    localDate1 = (event.getItem().getDate());
//                    note = (event.getItem().getNote());
//                    textArea.setValue(note);
//
//                });
    }

    private void configureGrid() {
        noticeGrid.addClassName("Grid");
        noticeGrid.setSizeFull();
        noticeGrid.setColumns("date","note");
        noticeGrid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    public void updateGrid() {

        LocalDate date = datePicker.getValue();
        List<Notice> all = noticeService.findAll();
        if(date==null)
        {
            Collections.reverse(all);
            noticeGrid.setItems(all);
        }else{
            noticeGrid.setItems(noticeService.findByDate(date));
        }
//        noticeGrid.addColumn(Notice::getDate).setHeader("Date").setFooter("Total: " + all.size() + " Notice");

    }
    private HorizontalLayout ToolBar() {

        datePicker.setPlaceholder("Filter by Date....");
        datePicker.setClearButtonVisible(true);
        datePicker.addValueChangeListener(e-> updateGrid());
        HorizontalLayout layout = new HorizontalLayout(datePicker,ButtonConfigure());
        layout.addClassName("Toolbar");
        return layout;
    }

    private Component ButtonConfigure() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addClickShortcut(Key.ENTER);
        save.addClickListener(event-> saveNotice());

        return new VerticalLayout(textArea,new HorizontalLayout(save));
    }


    private void saveNotice() {
        String note = textArea.getValue();
        LocalDate localDate = LocalDate.now();
        Notice notice = new Notice(localDate,note);
        noticeService.SaveNotice(notice);
        updateGrid();
    }
}
