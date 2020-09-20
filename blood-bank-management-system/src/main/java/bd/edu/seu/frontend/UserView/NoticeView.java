package bd.edu.seu.frontend.UserView;

import bd.edu.seu.Backend.Model.Notice;
import bd.edu.seu.Backend.Service.NoticeService;
import bd.edu.seu.frontend.DonorView.DonorMainView;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Route(value = "notice-view",layout = UserMainView.class)
@PageTitle("JBMK | Notice(user)")
public class NoticeView extends VerticalLayout {

    DatePicker datePicker = new DatePicker();
    Grid<Notice> noticeGrid = new Grid<>(Notice.class);
    private List<Notice> noticeList;
    private NoticeService noticeService;
    public NoticeView(NoticeService noticeService)
    {
        this.noticeService = noticeService;
        addClassName("list");
        setSizeFull();
        configureGrid();
        add(ToolBar(),noticeGrid);
        updateGrid();
    }

    private void configureGrid() {
        noticeGrid.addClassName("Grid");
        noticeGrid.setSizeFull();
        noticeGrid.setColumns("date","note");
        noticeGrid.getColumns().forEach(col-> col.setAutoWidth(true));
    }

    public void updateGrid() {

        LocalDate date = datePicker.getValue();
        if(date==null)
        {
            List<Notice> all = noticeService.findAll();
            Collections.reverse(all);
            noticeGrid.setItems(all);
        }else{
            noticeGrid.setItems(noticeService.findByDate(date));
        }

    }
    private HorizontalLayout ToolBar() {

        datePicker.setPlaceholder("Filter by Date....");
        datePicker.setClearButtonVisible(true);
        datePicker.addValueChangeListener(e-> updateGrid());
        HorizontalLayout layout = new HorizontalLayout(datePicker);
        layout.addClassName("Toolbar");
        return layout;
    }
}
