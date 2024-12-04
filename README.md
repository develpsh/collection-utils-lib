# be-library

> Inner Circle BE 2기 오픈소스 라이브러리

## 개요

- 자유 주제 오픈소스 라이브러리를 만들어봅니다.
- 개인 단위로 진행하여도 되고, 임의로 팀을 구성하여 팀 단위로 진행하여도 됩니다.
- Jitpack을 이용하여 GitHub에 라이브러리를 릴리즈해봅니다.

## 작업 방법

- 12월 7일 (토) 22시까지 `본인이름 - 라이브러리 주제`를 제목으로 한 [이슈를 등록](https://github.com/FC-InnerCircle-ICD2/be-library/issues/new/choose) (`OSS 프로젝트` 템플릿 사용)
  - 아직 작성이 끝나지 않은 이슈에는 `WIP` 라벨을 붙여주세요.
- 이슈를 통해 리더와 상의 후 프로젝트를 확정하고 진행. 확정된 프로젝트에는 리더가 `Go Ahead` 라벨을 붙입니다. 이 라벨이 붙은 경우는 바로 진행하시면 됩니다.
- ⚠️ **개인 계정에 프로젝트 저장소를 만듭니다**
  - 프로젝트 저장소 주소를 이슈 문서에 추가합니다.
- 프로젝트를 진행하면서 PR을 만들고 필요한 기능을 구현합니다.
- 리뷰를 받을 준비가 되면 `be-library` 저장소에 있는 본인 프로젝트 이슈에 `Needs Review` 라벨을 붙여 리더에게 리뷰를 요청합니다.
  - 해당 프로젝트의 오픈되어 있는 가장 최신 PR을 리뷰합니다. 혹시 다른 식으로 리뷰를 해야하면 말씀해주세요.
- 리뷰가 끝나면 리더는 이슈의 `Needs Review` 라벨을 제거하고 `Reviewed` 라벨을 붙입니다.
- 리뷰-피드백 과정이 끝나고 라이브러리 퍼블리싱까지 완료하면 `Published` 라벨을 붙이며 라이브러리 작성을 종료합니다.

## 내용

- 만들고자하는 라이브러리에 대한 소개를 [본 저장소](https://github.com/FC-InnerCircle-ICD2/be-library)의 이슈로 작성합니다.
- [이슈 템플릿](https://github.com/FC-InnerCircle-ICD2/be-library/blob/main/.github/ISSUE_TEMPLATE/oss-project.md)을 사용해주세요.
- JWT 토큰 파싱/검증 라이브러리, 패턴 매칭(e.g. [ts-pattern](https://github.com/gvergnaud/ts-pattern)), 날짜 Formatter, 금액 Formatter 등 자유 주제를 선정해주세요.
- 작성해주신 이슈를 기반으로 리더와 논의 한 후 프로젝트 주제를 확정하고 개발을 진행합니다.
