package TheChromaturge.vfx;

import TheChromaturge.DefaultMod;
import TheChromaturge.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Random;

public class GrayWispParticleEffect extends AbstractGameEffect {


    private static final Texture IMG1 = TextureLoader.getTexture(DefaultMod.makeVfxPath("GrayWispParticle.png"));
    private Texture img;
    private float posx;
    private float posy;
    private float Vfxtimer = 3.0f;
    public static Random r = new Random();
    private float scale;

    public GrayWispParticleEffect(float posx, float posy)
    {
        this.startingDuration = 2.5f;
        this.duration = this.startingDuration;
        img = IMG1;
        scale = 1;
        this.color = new Color(1.0f, 1.0f, 1.0f, 0.0f);
        this.posx = posx - 30 * Settings.scale + r.nextFloat() * 60 * Settings.scale;
        this.posy = posy - 30 * Settings.scale + r.nextFloat() * 60 * Settings.scale;
    }

    @Override
    public void update() {
        if(Vfxtimer > 1.5)
        {
            color.a +=  Gdx.graphics.getDeltaTime() * 0.5;
            scale += Gdx.graphics.getDeltaTime();
        }
        else if(color.a > 0)
        {
            scale -= Gdx.graphics.getDeltaTime();
            color.a -=  Gdx.graphics.getDeltaTime();
            if(this.color.a < 0)
            {
                color.a = 0;
            }
        }
        else
        {
            color.a = 0;
        }
        Vfxtimer -= Gdx.graphics.getDeltaTime();
        if (Vfxtimer <= 0)
        {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        spriteBatch.draw(img, posx - this.scale*Settings.scale * 10, posy- this.scale*Settings.scale * 10, 20 * Settings.scale * this.scale, 20*Settings.scale * this.scale);
        spriteBatch.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}