package Entities;

import static Entities.Constants.pixelInMeter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Obstaculo extends Actor {

    private Texture texture;

    private World world;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    private Body body;

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    private Fixture fixture;




    public Obstaculo(World world, Texture texture, Vector2 position) {
        this.world=world;
        this.texture=texture;

        BodyDef def= new BodyDef();
        def.position.set(position);
        def.type=BodyDef.BodyType.StaticBody;

        body= world.createBody(def);

        PolygonShape polygonShape= new PolygonShape();
        polygonShape.setAsBox(0.125f,0.5f);
        fixture= body.createFixture(polygonShape,1);
        fixture.setUserData("obstaculo");
        polygonShape.dispose();
        setSize(Constants.pixelInMeter,Constants.pixelInMeter);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x)*pixelInMeter,body.getPosition().y+1.3f*pixelInMeter);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    public void liberar(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
