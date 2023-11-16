package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorJugador extends Actor {

    private Texture texturaJ;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private boolean alive;

    public ActorJugador(Texture texturaJ) {
        this.texturaJ = texturaJ;
        setSize(texturaJ.getWidth(),texturaJ.getHeight());
        this.alive=true;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturaJ, getX(),getY());
    }
}
