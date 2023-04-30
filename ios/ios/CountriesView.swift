import SwiftUI
import KMMViewModelCore
import KMMViewModelSwiftUI
import common

class CountriesViewModel: common.CountriesViewModel, KMMViewModel {}

struct CountriesView: View {
    @StateViewModel var viewModel = CountriesViewModel()
    
    var body: some View {
        CountriesViewContent(uiState: viewModel.uiState, inputs: viewModel)
            .onAppear { viewModel.onReloadCountries() }
    }
}

struct CountriesViewContent: View {
    let uiState: CountriesUiState
    let inputs: CountriesViewModelInputs
    
    var body: some View {
        VStack {
            ScrollView {
                LazyVStack {
                    ForEach(uiState.countries, id: \.self) { country in
                        countryItem(country)
                    }
                }
            }
        }
    }
    
    @ViewBuilder
    private func countryItem(_ country: CountriesQuery.Country) -> some View {
        HStack {
            Text(country.emoji)
                .font(.largeTitle)
                .padding([.horizontal], 16)

            VStack {
                Text("\(country.name) (\(country.native))")
                    .font(.body)
                    .frame(maxWidth: .infinity, alignment: .leading)

                Text(country.continent.name)
                    .font(.body)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
        }
        .padding(4)
        .contentShape(Rectangle())
        .onTapGesture {
            inputs.onCountryClicked(country: country)
        }
    }
}
