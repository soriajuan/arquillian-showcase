package br.com.progpc.showcase.arquillian.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.progpc.showcase.arquillian.ejb.CrudService;
import br.com.progpc.showcase.arquillian.entity.Game;

@RunWith(Arquillian.class)
public class CrudGameTest {

	@Deployment
    public static Archive<?> createDeployment() {
        WebArchive jar = ShrinkWrap.create(WebArchive.class, "test.war")
            .addClass(CrudGameTest.class)
        	.addPackages(true, "br.com.solyos.showcase.arquillian")
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("jbossas-ds.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;
    }

	@EJB
	private CrudService crudService;

	@Test
	@ShouldMatchDataSet("dataset/crud/Game/game_insert.xml")
    public void incluir() throws Exception {
		crudService.save(new Game(1L, "Game 1"));
    }

	@Test
	@UsingDataSet("dataset/crud/Game/game_insert.xml")
	@ShouldMatchDataSet("dataset/crud/Game/game_update.xml")
    public void alterar() throws Exception {
		crudService.save(new Game(1L, "Game 1 - updated"));
    }

	@Test
	@UsingDataSet("dataset/crud/Game/game_all.xml")
	@ShouldMatchDataSet("dataset/crud/Game/game_delete.xml")
    public void excluir() throws Exception {
		crudService.delete(new Game(5L, "Game 5"));
    }

	@Test
	@UsingDataSet("dataset/crud/Game/game_all.xml")
    public void recuperarPorId() throws Exception {
		final Game game = crudService.findById(5L, Game.class);
		assertNotNull(game);
		assertEquals("Game 5", game.getTitle());
    }

}
