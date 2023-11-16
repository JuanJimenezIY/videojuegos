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
        this.stage.isDebugAll();
        this.jugador= new ActorJugador(texturaJugador);
        this.rocas= new ActorRoca(texturaRoca);
        this.stage.addActor(jugador);
        this.stage.addActor(rocas);
        this.jugador.setPosition(20,100);
        this.rocas.setPosition(400,100);
    }

    private void comprobarColisiones(){
        if (jugador.isAlive() &&     jugador.getX() + jugador.getWidth()> rocas.getX()){
            System.out.println("Colision detectada");
            jugador.setAlive(false);
            rocas.setAlive(false);
        }


    }
    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(2,0,5,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        comprobarColisiones();
    }

    @Override
    public void dispose() {
        super.dispose();
        texturaJugador.dispose();
        texturaRoca.dispose();
    }
}
