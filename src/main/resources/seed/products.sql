-- Production seed: 33 products (PostgreSQL, idempotent)

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (1, 'ダークインディゴ・ストレートデニム', '上品な深みのあるインディゴブルーが魅力のストレートデニム。
14オンスのセルビッジデニムを使用し、履くほどに身体になじむ質感がたまらない。
ジャケットにもTシャツにも相性バツグンで、オン・オフどちらのスタイルにもフィット。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (2, 'クラシック・ライトブルーデニム', '軽やかな色味と柔らかな穿き心地が特徴のライトブルーデニム。
ウォッシュ加工による自然な色落ちがカジュアルスタイルに抜け感をプラス。
春夏のコーデにもピッタリで、ロールアップして履けばさらに爽やかにキマる。', 9900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (3, 'ヴィンテージ・フェードスリムジーンズ', '絶妙な色落ちとシワ感がこなれ感を演出するスリムフィットジーンズ。
ストレッチ素材をブレンドしており、動きやすさも抜群。
スニーカーにもブーツにも合わせやすい万能デニムで、1本持っておけば間違いなし！', 13200.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (4, 'ホワイトプレーンTシャツ', 'コットン100%のシンプルな無地Tシャツ。軽くて肌触りが良く、オールシーズン活躍するベーシックアイテム。インナーとしてもアウターとしても使えるユニバーサルな一枚。', 4500.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (5, 'ブラックヘビーウェイトTシャツ', '6.2オンスの厚手コットンを使用したヘビーウェイトT。しっかりとした生地感で型崩れしにくく、洗濯を重ねるほど味が出るタフな一枚。ユニセックスなシルエット。', 5500.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (6, 'グレーポケットTシャツ', '左胸にポケットを配したリラックス感のある無地Tシャツ。杢グレーの落ち着いた色味はどんなボトムスとも相性抜群。ゆとりあるシルエットで快適な着心地。', 4200.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (7, 'ネイビーボーダーTシャツ', 'フランス・バスクをルーツに持つマリン風ボーダーTシャツ。ネイビーと白の細かいボーダーがクラシカルな雰囲気を演出。ジーンズとの相性が特に良い。', 5900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (8, 'オーバーサイズグラフィックTシャツ', 'ドロップショルダーのオーバーサイズシルエットにヴィンテージ風グラフィックをプリント。ルーズな着こなしが今季のムードにマッチ。ショーツやスキニーデニムとの組み合わせがおすすめ。', 6900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (9, 'ホワイトオックスフォードシャツ', '上質なコットン100%のオックスフォード生地を使用した定番シャツ。清潔感のあるホワイトは、ジーンズからスラックスまで幅広くコーディネートできる万能アイテム。形態安定加工済み。', 8900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (10, 'ネイビーストライプシャツ', 'ネイビーとホワイトのクリーンなストライプ柄シャツ。ビジネスカジュアルにもオフスタイルにも対応できる汎用性の高い一枚。ポプリン素材で春夏の着用に最適。', 9800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (11, 'ライトブルーリネンシャツ', '天然リネン100%の軽やかなシャツ。吸湿性・速乾性に優れ、蒸し暑い季節でも快適に着用できる。ライトブルーの涼しげな色味がサマースタイルを格上げ。', 9500.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (12, 'クルーネックスウェット・グレー', '裏毛素材の定番クルーネックスウェット。毛玉になりにくい高密度ニットを採用し、長く愛用できる一枚。ミックスグレーのシンプルな佇まいがあらゆるコーデに馴染む。', 8900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (13, 'ジップアップパーカー・ブラック', 'フロントジップのオーソドックスなパーカー。厚手の裏起毛素材で秋冬の防寒に最適。大きめのカンガルーポケットと深めのフードが実用的。インナーとしてもアウターとしても活躍。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (14, 'プルオーバーパーカー・オートミール', '温かみのあるオートミールカラーのプルオーバーパーカー。柔らかな肌触りとゆったりとしたシルエットがリラックスタイムにぴったり。シンプルな刺繍ロゴがさりげないアクセント。', 11800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (15, 'コーデュロイシャツジャケット・ブラウン', 'コーデュロイ素材を使ったシャツとジャケットの中間アイテム。秋冬の羽織りに最適で、インナーのTシャツやニットを選ばない。ブラウンの深みある色味が上品な印象を与える。', 18800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (16, 'テーラードジャケット・ネイビー', '上質なウールブレンド生地を使ったテーラードジャケット。シングルブレスト・2ボタンの正統派シルエット。スラックスとのセットアップ使いはもちろん、デニムへのジャケパンスタイルにも対応。', 28000.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (17, 'ウールチェスターコート・キャメル', 'ウール60%混紡のリッチな質感が魅力のチェスターコート。キャメルカラーは秋冬に最も映える色として人気。すっきりとしたIラインシルエットでスタイルアップ効果抜群。', 45000.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (18, 'フリースジャケット・グリーン', '軽量で保温性に優れたポーラーフリース素材のジャケット。アウトドアシーンからタウンユースまで幅広く活躍。フォレストグリーンの落ち着いた色味がナチュラルスタイルにマッチ。', 9900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (19, 'チノパンツ・ベージュ', 'コットンツイル素材の定番チノパンツ。ストレートシルエットで脚のラインを自然に整える。ベージュは最もコーディネートしやすいカラーで、オン・オフ問わず重宝するアイテム。', 9900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (20, 'スラックス・グレー', 'ウールライクな素材感で高見えするテーパードスラックス。センタープレスが入りシャープな印象を演出。ジャケットやシャツとのフォーマルスタイルはもちろん、スニーカーを合わせたリラックスコーデにも対応。', 14800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (21, 'ワイドカーゴパンツ・オリーブ', 'ワイドシルエットに大型サイドポケットを備えたカーゴパンツ。オリーブカラーがミリタリーテイストを高めつつ、都会的なスタイリングにも馴染む。機能性とトレンド感を両立。', 13800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (22, 'リネンワイドパンツ・ホワイト', 'リネン素材のナチュラルで涼感あるワイドパンツ。ウエストはゴム仕様でストレスフリーな着用感。ホワイトカラーはサマーシーズンの主役として、クリーンなスタイリングを実現。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (23, 'ショートパンツ・ネイビー', '膝上丈のショートパンツ。コットンリップストップ素材で軽量かつ耐久性に優れる。ネイビーのシンプルなデザインはボーダーTやシャツと相性抜群。夏のリゾートスタイルにもおすすめ。', 7900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (24, 'ケーブルニットセーター・クリーム', 'ウール混の立体的なケーブル編みが美しいセーター。クリームカラーは肌馴染みが良く、顔周りを明るく見せる効果も。程よくボリュームのあるシルエットが秋冬のスタイリングを格上げ。', 18800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (25, 'モックネックリブニット・ブラック', 'タートルより低めのモックネックとリブ編みが特徴のフィットニット。身体のラインを程よく拾うスリムシルエットで、コートやジャケットのインナーとしても機能的。オールシーズン対応。', 15800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (26, 'レザーベルト・ブラウン', '本革を使用した上質なレザーベルト。シンプルなスクエアバックルがどんなコーデにも合わせやすい。裏面はスエード仕様で高級感あり。チノパンツやジーンズとの相性が抜群。', 5900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (27, 'キャンバストートバッグ', '10オンスの厚手キャンバス素材のトートバッグ。A4サイズが楽に入る収納力と、シンプルなデザインが使い勝手抜群。エコバッグとしてもデイリーバッグとしても長く愛用できる定番品。', 7900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (28, 'レザービジネスウォレット', '本牛革を使用した薄型二つ折り財布。カードポケット8枚分と札入れ、小銭入れを備えたコンパクトな設計。ブラックの洗練されたデザインはビジネスシーンでの使用に最適。', 12800.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (29, 'バックパック・ブラック', '15インチノートPCが収納できるクッション付きPCスリーブを内蔵したバックパック。撥水加工のナイロン素材で急な雨にも対応。シンプルなデザインはビジネスからデイリーまで幅広く対応。', 22000.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (30, 'ニットキャップ・グレー', 'ウールアクリル混紡のベーシックなニットキャップ。伸縮性のあるリブ編みが頭にフィットし、防寒性も高い。シンプルなデザインは男女問わず使えるユニセックスアイテム。', 3900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (31, 'ウールキャスケット・ネイビー', 'ウールブレンドのクラシックなキャスケット（ハンチングハット）。ツバ付きのデザインがスタイリッシュな印象を演出。ネイビーカラーはシーズンレスに活躍し、コーデのポイントになる。', 7200.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (32, 'レザーキーホルダー', '植物タンニンなめしの本革を使用したシンプルなキーホルダー。使い込むほどに革が育ち、独自のエイジングを楽しめる。カバンの外ポケットに付けてもサマになるミニマルデザイン。', 2400.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;

INSERT INTO products (product_id, name, description, price, deleted, created_at, updated_at)
VALUES (33, 'ウールチェックマフラー', 'ウール85%混紡のクラシックなチェック柄マフラー。グレー×チャコールの落ち着いた配色はどんなコートとも相性抜群。大判サイズで防寒性も高く、秋冬の必需品。', 8900.00, FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_id) DO NOTHING;
