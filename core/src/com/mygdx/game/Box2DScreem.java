package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.MyGdxGame;

import javax.swing.Renderer;

public class Box2DScreem extends BaseScreen {


    private World world;
    private Box2DDebugRenderer renderer;

    private OrthographicCamera camera;

    private Body body,sueloBody,rocaBody;

    private boolean colisionDetectada=false;
    private Fixture fixture, sueloFixture,rocaFixture;
    public Box2DScreem(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float velocidadY= body.getLinearVelocity().y;
        body.setLinearVelocity(5,velocidadY);
        if (Gdx.input.justTouched() && colisionDetectada==false){
            saltar();


        }

        if (colisionDetectada==true){
            body.setLinearVelocity(0,0);
        }

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }

    @Override
    public void show() {
        world= new World(new Vector2(0,-10),true);
        renderer= new Box2DDebugRenderer();
        camera= new OrthographicCamera(32,18);
        camera.translate(0,1);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                Fixture fixtureA= contact.getFixtureA();
                Fixture fixtureB= contact.getFixtureB();
                if (fixtureA==fixture && fixtureB==rocaFixture){

                    colisionDetectada=true;

                }

                if (fixtureB==fixture && fixtureA==rocaFixture){

                    colisionDetectada=true;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        BodyDef sueloBodyDef= createsueloBodyDef();
        BodyDef bodyDef= createBodyDef();
        BodyDef rocaBodyDef= createRocaBodyDef();


        body= world.createBody(bodyDef);
        sueloBody=world.createBody(sueloBodyDef);
        rocaBody=world.createBody(rocaBodyDef);

        PolygonShape polygonShape= new PolygonShape();
        polygonShape.setAsBox(1,1);
        fixture= body.createFixture(polygonShape,1);

        PolygonShape polygonShape2= new PolygonShape();
        polygonShape2.setAsBox(500,1);
        sueloFixture= sueloBody.createFixture(polygonShape2,1);

        PolygonShape polygonShape3= new PolygonShape();

        Vector2[] vertices= new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);
        polygonShape3.set(vertices);
        rocaFixture= rocaBody.createFixture(polygonShape3,1);
    }

    private BodyDef createBodyDef(){
        BodyDef def= new BodyDef();
        def.position.set(-20,-5.5f);
        def.type=BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createsueloBodyDef(){
        BodyDef def= new BodyDef();
        def.position.set(0,-7);
        def.type=BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createRocaBodyDef(){
        BodyDef def= new BodyDef();
        def.position.set(5f,-5.5f);
        def.type=BodyDef.BodyType.StaticBody;
        return def;
    }
    @Override
    public void dispose() {
        body.destroyFixture(fixture);
        body.destroyFixture(sueloFixture);
        renderer.dispose();
        world.dispose();
        world.destroyBody(this.body);
    }
    private void saltar(){
        Vector2 position=body.getPosition();
        body.applyLinearImpulse(0,20, position.x, position.y, true);
       // body.applyAngularImpulse(20.5f,true);
    }
}
