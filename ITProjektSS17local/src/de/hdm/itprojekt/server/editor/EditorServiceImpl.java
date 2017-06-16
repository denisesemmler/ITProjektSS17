package de.hdm.itprojekt.server.editor;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BeteiligungMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.ProjektmarktplatzMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.EditorService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EditorServiceImpl  extends RemoteServiceServlet implements EditorService {

	//Instanzen der DB-Mapper
	private AusschreibungMapper aMapper = AusschreibungMapper.ausschreibungMapper();
	private BeteiligungMapper bMapper = BeteiligungMapper.beteiligungMapper();
	//private EigenschaftMapper eMapper = EigenschaftMapper.eigenschaftMapper();
	private ProfilMapper pMapper = ProfilMapper.profilMapper();
	private ProjektMapper proMapper = ProjektMapper.projektMapper();
	private ProjektmarktplatzMapper pmMapper = ProjektmarktplatzMapper.projektmarktplatzMapper();
	private TeilnehmerMapper tMapper = TeilnehmerMapper.teilnehmerMapper();
}
