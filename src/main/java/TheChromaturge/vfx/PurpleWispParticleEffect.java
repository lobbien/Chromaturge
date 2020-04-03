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

public class PurpleWispParticleEffect extends AbstractGameEffect {


    private static final Texture IMG1 = TextureLoader.getTexture(DefaultMod.makeVfxPath("PurpleWispParticle.png"));
    private Texture img;
    private float posx;
    private float posy;
    private float Vfxtimer = 2.0f;
    public static Random r = new Random();
    private float scale;
    private boolean flipped;

    public PurpleWispParticleEffect(float posx, float posy, boolean flipped)
    {
        this.startingDuration = 1.0f;
        this.duration = this.startingDuration;
        img = IMG1;
        scale = 0.6f;
        float y = r.nextFloat();
        this.color = new Color(1.0f, 0.0f , 1.0f, 0.6f);
        this.posx = posx;
        this.posy = posy;
        this.flipped = flipped;
    }

    @Override
    public void update() {
        color.a -=  Gdx.graphics.getDeltaTime() * 0.2f;
        color.r -=  Gdx.graphics.getDeltaTime() * 0.2f;
        Vfxtimer -= Gdx.graphics.getDeltaTime();
        this.scale += Gdx.graphics.getDeltaTime() * 0.2;
        if(!this.flipped)
        {
            this.posx -= 20*Gdx.graphics.getDeltaTime()*Settings.scale;
        }
        else
        {
            this.posx += 20*Gdx.graphics.getDeltaTime()*Settings.scale;
        }
        if (Vfxtimer <= 0)
        {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        spriteBatch.draw(img, posx - this.scale*Settings.scale * 48, posy- this.scale*Settings.scale * 48, 96 * Settings.scale * this.scale, 96*Settings.scale * this.scale, 0, 0, img.getWidth(),img.getHeight(), flipped, false);
        spriteBatch.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}

