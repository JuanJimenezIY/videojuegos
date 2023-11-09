package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.ActorJugador;
import actores.ActorRoca;

public class MainGameScreen extends BaseScreen{
    public MainGameScreen(MyGdxGame game) {

        super(game);
        texturaJugador = new Texture("dino100.png");
        texturaRoca = new Texture("roka.png");
    }

    private Texture texturaJugador;
    private Texture texturaRoca;
    private Stage stage;
    private ActorJugador jugador;

    private ActorRoca rocas;
    @Override
    public void show() {

        this.stage =new Stage();
        this.jugador= new ActorJugador(texturaJugador);
        this.rocas= new ActorRoca(texturaRoca);
        this.stage.addActor(jugador);
        this.stage.addActor(rocas);
        this.jugador.setPosition(20,100);
        this.rocas.setPosition(400,100);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(5,0,5,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        texturaJugador.dispose();
        texturaRoca.dispose();
    }
}
