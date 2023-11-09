package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorRoca extends Actor {
    private Texture texturaR;

    public ActorRoca(Texture texturaR) {
        this.texturaR = texturaR;
    }

    @Override
    public void act(float delta) {

        setX(getX()-250*delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturaR, getX(),getY());
    }
}
