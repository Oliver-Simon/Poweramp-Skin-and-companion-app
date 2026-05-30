.class public Lcom/poweramp/v3/dais/SkinInfoActivity;
.super Landroid/app/Activity;
.source "SkinInfoActivity.java"


# static fields
.field private static final TAG:Ljava/lang/String; = "SkinInfoActivity"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 12
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method

.method public static getPowerampPackageName(Landroid/content/Context;)Ljava/lang/String;
    .locals 2

    .line 27
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    new-instance v0, Landroid/content/Intent;

    const-string v1, "com.maxmpz.audioplayer.API_COMMAND"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1}, Landroid/content/pm/PackageManager;->resolveService(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 28
    iget-object v0, p0, Landroid/content/pm/ResolveInfo;->serviceInfo:Landroid/content/pm/ServiceInfo;

    if-eqz v0, :cond_0

    .line 29
    iget-object p0, p0, Landroid/content/pm/ResolveInfo;->serviceInfo:Landroid/content/pm/ServiceInfo;

    iget-object p0, p0, Landroid/content/pm/ServiceInfo;->packageName:Ljava/lang/String;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    move-exception p0

    const-string v0, "SkinInfoActivity"

    const-string v1, ""

    .line 32
    invoke-static {v0, v1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 0

    .line 17
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    const p1, -0x7ffbffca

    .line 18
    invoke-virtual {p0, p1}, Lcom/poweramp/v3/dais/SkinInfoActivity;->setContentView(I)V

    return-void
.end method

.method public openPowerampThemeSettings(Landroid/view/View;)V
    .locals 2

    .line 53
    invoke-static {p0}, Lcom/poweramp/v3/dais/SkinInfoActivity;->getPowerampPackageName(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_0

    const p1, -0x7ff9fc90

    const/4 v0, 0x1

    .line 55
    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;II)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    return-void

    .line 58
    :cond_0
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.intent.action.MAIN"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v1, "com.maxmpz.audioplayer.SettingsActivity"

    .line 59
    invoke-virtual {v0, p1, v1}, Landroid/content/Intent;->setClassName(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-result-object p1

    const-string v0, "open"

    const-string v1, "theme"

    .line 60
    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-result-object p1

    .line 61
    invoke-virtual {p0}, Lcom/poweramp/v3/dais/SkinInfoActivity;->getPackageName()Ljava/lang/String;

    move-result-object v0

    const-string v1, "theme_pak"

    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-result-object p1

    const v0, -0x7ff8f164

    const-string v1, "theme_id"

    .line 62
    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    move-result-object p1

    .line 63
    invoke-virtual {p0, p1}, Lcom/poweramp/v3/dais/SkinInfoActivity;->startActivity(Landroid/content/Intent;)V

    .line 64
    invoke-virtual {p0}, Lcom/poweramp/v3/dais/SkinInfoActivity;->finish()V

    return-void
.end method

.method public startWithSampleSkin(Landroid/view/View;)V
    .locals 2

    .line 38
    invoke-static {p0}, Lcom/poweramp/v3/dais/SkinInfoActivity;->getPowerampPackageName(Landroid/content/Context;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_0

    const p1, -0x7ff9fc90

    const/4 v0, 0x1

    .line 40
    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;II)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    return-void

    .line 43
    :cond_0
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.intent.action.MAIN"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v1, "com.maxmpz.audioplayer.StartupActivity"

    .line 44
    invoke-virtual {v0, p1, v1}, Landroid/content/Intent;->setClassName(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-result-object p1

    .line 45
    invoke-virtual {p0}, Lcom/poweramp/v3/dais/SkinInfoActivity;->getPackageName()Ljava/lang/String;

    move-result-object v0

    const-string v1, "theme_pak"

    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    move-result-object p1

    const v0, -0x7ff8f164

    const-string v1, "theme_id"

    .line 46
    invoke-virtual {p1, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    move-result-object p1

    .line 47
    invoke-virtual {p0, p1}, Lcom/poweramp/v3/dais/SkinInfoActivity;->startActivity(Landroid/content/Intent;)V

    .line 49
    invoke-virtual {p0}, Lcom/poweramp/v3/dais/SkinInfoActivity;->finish()V

    return-void
.end method
