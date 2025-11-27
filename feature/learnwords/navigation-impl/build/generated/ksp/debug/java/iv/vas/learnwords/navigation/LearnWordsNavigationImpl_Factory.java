package iv.vas.learnwords.navigation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class LearnWordsNavigationImpl_Factory implements Factory<LearnWordsNavigationImpl> {
  @Override
  public LearnWordsNavigationImpl get() {
    return newInstance();
  }

  public static LearnWordsNavigationImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LearnWordsNavigationImpl newInstance() {
    return new LearnWordsNavigationImpl();
  }

  private static final class InstanceHolder {
    private static final LearnWordsNavigationImpl_Factory INSTANCE = new LearnWordsNavigationImpl_Factory();
  }
}
