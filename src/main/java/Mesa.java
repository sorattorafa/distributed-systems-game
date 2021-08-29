import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Mesa implements Serializable {
  private Integer id;
  private List<Jogador> players_list = new ArrayList<Jogador>();
  private List<MaoJogador> players_hand = new ArrayList<MaoJogador>();
  private List<StatusJogador> players_status = new ArrayList<StatusJogador>();

  private Baralho baralho;
  private Integer total_cash;
  
  public Mesa(){
  
  }

  public void set_id(Integer id) {
    this.id = id;
  }
  public Integer get_id() {
    return this.id;
  }

   public void set_baralho(Baralho baralho) {
    this.baralho = baralho;
  }
  public Baralho get_baralho() {
    return this.baralho;
  }

  public void set_total_cash(Integer total_cash) {
    this.total_cash = total_cash;
  }
  public Integer get_total_cash() {
    return this.total_cash;
  }
    
   public void add_player(Jogador jogador) {
    this.players_list.add(jogador);
  }
  public List<Jogador> players_list() {
    return this.players_list;
  }


  public void add_player_status(StatusJogador jogador) {
    this.players_status.add(jogador);
  }
  
  public List<StatusJogador> players_status() {
    return this.players_status;
  }


  public void add_mao_jogador(MaoJogador maojogador) {
    this.players_hand.add(maojogador);
  }
  public List<MaoJogador> players_hand() {
    return this.players_hand;
  }

  public Jogador get_opponent(String player_nickname) {
    for (Jogador jogador : this.players_list) {
      if (!jogador.get_nickname().equals(player_nickname)) {
        return jogador;
      }
    }
    return null;
  }

  public MaoJogador get_opponent_hand(String player_nickname){
      Jogador opponent = this.get_opponent(player_nickname);

      for (MaoJogador maojogador : this.players_hand) {
        if (maojogador.get_player_id().equals(opponent.get_id())) {
          return maojogador;
        }
      }
      return null;
  }

  List<Carta> get_player_cards(Integer player_id) {       
    for (MaoJogador maojogador : this.players_hand) {
      if (maojogador.get_player_id().equals(player_id)) {
        return maojogador.get_player_hand();
      }
    }
    return null;
  }

  public Integer get_player_points(List<Carta> player_hand) {
    Integer points = 0;
    for (Carta carta : player_hand) {
      Integer flag = 0;
      if (carta.get_name().equals("A")) {
        for (Carta carta2 : player_hand) {
          if (carta2.get_name().equals("J") || carta2.get_name().equals("Q") || carta2.get_name().equals("K")) {
            points += 11;
            flag = 1;
            break;
          }
        }
      }
      if (flag == 0) {
        points += carta.get_value();
      }
    }
    return points;
  }

  public Integer get_player_statusCode(Integer player_id) {
    for (StatusJogador statusjogador : this.players_status) {
      if (statusjogador.get_player_id().equals(player_id)) {
        return statusjogador.get_player_status();
      }
    }
    return null;
  }

  public StatusJogador get_players_status(Integer player_id) {
    for (StatusJogador statusjogador : this.players_status) {
      if (statusjogador.get_player_id().equals(player_id)) {
        return statusjogador;
      }
    }
    return null;
  }

}