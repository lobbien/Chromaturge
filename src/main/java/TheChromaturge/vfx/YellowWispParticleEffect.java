package TheChromaturge.vfx;

import TheChromaturge.DefaultMod;
import TheChromaturge.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Random;

public class YellowWispParticleEffect extends AbstractGameEffect {


    private static final Texture IMG1 = TextureLoader.getTexture(DefaultMod.makeVfxPath("YellowWispParticle.png"));
    private Texture img;
    private float posx;
    private float posy;
    private float Vfxtimer = 1.5f;
    public static Random r = new Random();
    private float scale;
    private float mx;
    private float my;
    private float angle;

    public YellowWispParticleEffect(float posx, float posy)
    {
        this.startingDuration = 1.0f;
        this.duration = this.startingDuration;
        img = IMG1;
        scale = 1;
        this.color = new Color(MathUtils.random(0.8f, 1.0f), MathUtils.random(0.8f, 1.0f), 0f, 0.0f);
        this.posx = posx + (1 - MathUtils.random(1.0f, 2.0f))* Settings.scale * 10;
        this.posy = posy + (1 - MathUtils.random(1.0f, 2.0f))* Settings.scale * 10;
        this.mx = MathUtils.random(1.0f, -1.0f);
        if(mx <= 0)
        {
            int pn = MathUtils.random(0, 1);
            if(pn == 0)
            {
                my = (1.0f + mx) * -1.0f;
            }
            else
            {
                my = (1.0f + mx);
            }
        }
        else
        {
            int pn = MathUtils.random(0, 1);
            if(pn == 0)
            {
                my = (1.0f - mx) * -1.0f;
            }
            else
            {
                my = (1.0f - mx);
            }
        }
        angle = MathUtils.atan2(my, mx)/MathUtils.PI2 * 360.0f - 90.0f;
    }

    @Override
    public void update() {
        if(Vfxtimer > 0.6 && Vfxtimer < 1.2)
        {
            color.a +=  Gdx.graphics.getDeltaTime();
        }
        else if(Vfxtimer < 0.6 && color.a > 0)
        {
            color.a -=  Gdx.graphics.getDeltaTime();
        }
        else
        {
            color.a = 0;
        }
        Vfxtimer -= Gdx.graphics.getDeltaTime();
        this.posx += mx * Gdx.graphics.getDeltaTime() * 20;
        this.posy += my * Gdx.graphics.getDeltaTime() * 20;
        if (Vfxtimer <= 0)
        {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        //spriteBatch.draw(img, posx - this.scale*Settings.scale * 10, posy- this.scale*Settings.scale * 10, 20 * Settings.scale * this.scale, 20*Settings.scale * this.scale);
        spriteBatch.draw(img, this.posx - 10 * Settings.scale, this.posy - 10 * Settings.scale, (float)img.getWidth() / 2.0F, (float)img.getHeight() / 2.0F, (float)img.getWidth(), (float)img.getHeight(), Settings.scale, Settings.scale , angle, 0, 0, 20, 20, false, false);
        spriteBatch.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {

    }
}
