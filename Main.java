import repositories.LawFirmRepository;
import repositories.LawFirmRepositoryImpl;
import services.LawFirmService;
import services.LawFirmServiceImpl;
import views.LawFirmTerminalViewImpl;
import views.LawFirmView;

public class Main {
    public static void main(String[] args) {
        LawFirmRepository lawFirmRepository = new LawFirmRepositoryImpl();
        LawFirmService lawFirmService = new LawFirmServiceImpl(lawFirmRepository);
        LawFirmView lawFirmView = new LawFirmTerminalViewImpl(lawFirmService);
        lawFirmView.run();
    }
}
