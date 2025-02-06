import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import spark.Spark;
import static spark.Spark.get;
import static spark.Spark.staticFiles;
public class FasesLua {

    private static final double CICLO_LUNAR = 29.53;
    private static final String[] FASES_LUA = {
        "Lua Nova",
        "Lua Crescente",
        "Quarto Crescente",
        "Crescente Gibosa",
        "Lua Cheia",
        "Minguante Gibosa",
        "Quarto Minguante",
        "Lua Minguante"
    };

    public static void main(String[] args) {
        Spark.port(3000);
        staticFiles.location("/static");
 //cors

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
            response.header("Access-Control-Allow-Headers", "*");
        });



        get("/", (req, res) -> {
            res.type("text/html");
            return "<html><head><meta http-equiv='refresh' content='0; URL=/index.html'></head></html>";
        });
    
        get("/fase-lua", (req, res) -> {
            String dataInput = req.queryParams("data");
            LocalDate dataAlvo = LocalDate.parse(dataInput, DateTimeFormatter.ISO_DATE);
            String faseLua = calcularFaseLua(dataAlvo);
            return faseLua;
        });
    }
    private static String calcularFaseLua(LocalDate dataAlvo) {
        LocalDate luaNovaConhecida = LocalDate.of(2023, 10, 14); // data de uma Lua Nova qualquer
        long diasDesdeLuaNova = ChronoUnit.DAYS.between(luaNovaConhecida, dataAlvo);
        double posicaoCiclo = diasDesdeLuaNova % CICLO_LUNAR;

        if (posicaoCiclo < 1.84) return FASES_LUA[0];
        else if (posicaoCiclo < 5.53) return FASES_LUA[1];
        else if (posicaoCiclo < 9.23) return FASES_LUA[2];
        else if (posicaoCiclo < 12.92) return FASES_LUA[3];
        else if (posicaoCiclo < 16.61) return FASES_LUA[4];
        else if (posicaoCiclo < 20.30) return FASES_LUA[5];
        else if (posicaoCiclo < 23.99) return FASES_LUA[6];
        else return FASES_LUA[7];
    }
}