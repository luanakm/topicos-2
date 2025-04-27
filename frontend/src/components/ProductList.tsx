import { Produto } from "@/interfaces/Produto";
import ProductCard from "./ProductCard";

const produtos: Produto[] = [
    {
        nome: 'Garrafa Rosa Plástico 1L',
        descricao: 'As garrafas modelo Squeeze são práticas para atividades físicas, graças ao canudo e à trava anti-vazamento. O modelo da Lyor inclui marcações de horários e frases motivacionais para ajudar na hidratação diária.',
        preco: 48,
        categoria: 'Garrafas',
        imagem: 'https://maluvalehomedecor.cdn.magazord.com.br/img/2022/12/produto/2537/garrafa-squeeze-para-agua-de-plastico-com-marcadores-rosa-1l-lyor-2797.jpg?ims=600x600',
        disponilibidade: 'Em estoque'
    },
    {
        nome: 'Bloco de anotações',
        descricao: 'Bloco de anotações pautado 12x20 Preta',
        preco: 35,
        categoria: 'Papelaria',
        imagem: 'https://cicero.vtexassets.com/arquivos/ids/186824/7899866826731-Bloco-Wire-o-Classica-Pautado-12x20-Preta--1-.jpg?v=638604453770230000',
        disponilibidade: 'Em estoque'
    },
    {
        nome: 'Caneta Gel 0.7 mm Cis',
        descricao: 'A caneta queridinha da Cis chegou em sua versão gel retrátil. Com a ponta de 0.7mm, ela possui uma escrita macia e é ideal para o dia a dia escolar ou profissional. ',
        preco: 25,
        categoria: 'Papelaria',
        imagem: 'https://images.tcdn.com.br/img/img_prod/1106500/caneta_gel_0_7_mm_cis_pentonic_retratil_19169_1_dc6a3c0591c1c4fff25decf3bee22046.jpg',
        disponilibidade: 'Em estoque'
    },
    {
        nome: 'Pincel Marcador Multiuso BIC Intensity',
        descricao: 'Com a linha de marcadores permanente BIC Marking você garante toda a qualidade necessária para realizar suas tarefas diárias e trabalhos impecáveis.',
        preco: 20,
        categoria: 'Papelaria',
        imagem: 'https://img.kalunga.com.br/fotosdeprodutos/616905z.jpg',
        disponilibidade: 'Em estoque'
    },
    {
        nome: 'Marca Texto Cores Pastéis',
        descricao: 'O Marcador de Texto com Grip 4 cores Pastéis BIC Marking segue a tendência das cores pastel no mercado de papelaria. Ele oferece cores vibrantes e duradouras, uma ponta chanfrada resistente para traçados finos e grossos, além de um grip emborrachado para maior controle. Sua tinta à base de água evita borrões e não marca a folha de trás. Ideal para destacar e sublinhar com precisão e estilo!',
        preco: 22,
        categoria: 'Papelaria',
        imagem: 'https://img.kalunga.com.br/fotosdeprodutos/620334d.jpg',
        disponilibidade: 'Em estoque'
    },
    {
        nome: 'Papel para Lembrete Colorido',
        descricao: 'Miolo personalizado em papel Super Bond 50 g/m² (rosa, azul, amarelo, verde e branco)',
        preco: 18,
        categoria: 'Papelaria',
        imagem: 'https://img.kalunga.com.br/fotosdeprodutos/036567d.jpg',
        disponilibidade: 'Em estoque'
    },
    {
        nome: ' Garrafa Térmica de Aço Inox Preta',
        descricao: 'Esta garrafa térmica combina design moderno e minimalista com material durável, sendo feita de aço inox com parede dupla. Sua eficiência no isolamento térmico garante que as bebidas permaneçam na temperatura desejada por horas. Tem uma capacidade prática de 500ml, ideal para o uso diário ou viagens. Para manter limpa, basta usar água corrente e sabão.',
        preco: 40,
        categoria: 'Garrafas',
        imagem: 'https://images.tcdn.com.br/img/img_prod/1189908/garrafa_termica_de_aco_inox_explorer_preta_650ml_4878_lyor_5759_6_ca21a3469153d2c7ab5f3152d1409cc6.jpg',
        disponilibidade: 'Em estoque'
    },
    {
        nome: 'Agenda diária mini 2025, Azul e Cinza,',
        descricao: 'Organização e estilo andando lado a lado com a agenda diária da Dac. Mantenha seu ano organizado e acompanhe seus compromissos sem perder nada, com espaço dedicado para inserção de dados pessoais e calendário completo você vai conseguir panejar seu ano de uma forma simples com essa agenda permanente.',
        preco: 30,
        categoria: 'Papelaria',
        imagem: 'https://img.kalunga.com.br/fotosdeprodutos/010200d.jpg',
        disponilibidade: 'Em estoque'
    }
]



export default function ProductList() {

    return (
        <div className="bg-white">
            <div className="mx-auto max-w-2xl px-4 py-16 sm:px-6 sm:py-24 lg:max-w-7xl lg:px-8">
                <h2 className="text-2xl font-bold tracking-tight text-gray-900">Produtos</h2>

                <div className="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8">
                    {produtos.map((product, index) => (
                        <div key={index} className="group">
                            <ProductCard key={index}produto={product}></ProductCard>
                        </div>
                        ))}
                </div>
            </div>
        </div>
    );

}