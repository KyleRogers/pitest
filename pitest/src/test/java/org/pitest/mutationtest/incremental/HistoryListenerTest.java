package org.pitest.mutationtest.incremental;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pitest.mutationtest.execute.MutationStatusTestPair;
import org.pitest.mutationtest.instrument.MutationMetaData;
import org.pitest.mutationtest.report.MutationTestResultMother;
import org.pitest.mutationtest.results.DetectionStatus;
import org.pitest.mutationtest.results.MutationResult;

public class HistoryListenerTest {

  private HistoryListener testee;

  @Mock
  private HistoryStore    store;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.testee = new HistoryListener(this.store);
  }

  @Test
  public void shouldRecordMutationResults() {
    final MutationResult mr = makeResult();
    final MutationMetaData metaData = MutationTestResultMother
        .createMetaData(mr);
    this.testee.handleMutationResult(metaData);
    verify(this.store).recordResult(mr);
  }

  private MutationResult makeResult() {
    final MutationResult mr = new MutationResult(
        MutationTestResultMother.createDetails(), new MutationStatusTestPair(0,
            DetectionStatus.KILLED));
    return mr;
  }

}