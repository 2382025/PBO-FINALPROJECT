import config.Database;
import repositories.LawFirmRepository;
import repositories.LawFirmRepositoryDbImpl;
import repositories.LawFirmRepositoryImpl;
import services.LawFirmService;
import services.LawFirmServiceImpl;
import views.LawFirmTerminalViewImpl;
import views.LawFirmView;

public class Main {
    public static void main(String[] args) {

        Database database = new Database("lawfirm", "root", "", "localhost", "3306");
        database.setup();

        LawFirmRepository lawFirmRepository = new LawFirmRepositoryDbImpl(database);
        LawFirmService lawFirmService = new LawFirmServiceImpl(lawFirmRepository);
        LawFirmView lawFirmView = new LawFirmTerminalViewImpl(lawFirmService);
        lawFirmView.run();
    }
}
